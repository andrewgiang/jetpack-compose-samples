package com.detroitlabs.composeplayground.expenseapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.detroitlabs.composeplayground.expenseapp.api.Api
import com.detroitlabs.composeplayground.expenseapp.api.FakeAPI
import com.detroitlabs.composeplayground.expenseapp.model.Expense
import com.detroitlabs.composeplayground.expenseapp.model.Profile
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

typealias UiModel = Pair<Profile, List<Expense>>

class ExpenseViewModel(private val api: Api = FakeAPI) : ViewModel() {
  private val compositeDisposable = CompositeDisposable()
  private val repeatSource = BehaviorSubject.create<Unit>()
  private val _uiModel = MutableLiveData<Async<UiModel>>()

  val uiModel: LiveData<Async<UiModel>> = _uiModel

  init {
    compositeDisposable.add(fetchData().subscribe {
      _uiModel.value = it
    })
  }

  private fun fetchData(): Observable<Async<UiModel>> {
    return api.getProfile().zipWith(api.getExpenses(), mapToUiModel())
        .toObservable()
        .startWith(Async.Loading)
        .onErrorReturn { error ->
          Async.Error(error)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .repeatWhen { repeatSource }
  }

  private fun mapToUiModel(): BiFunction<Profile, List<Expense>, Async<UiModel>> {
    return BiFunction<Profile, List<Expense>, Async<UiModel>> { profile, expenses ->
      Async.Data(profile to expenses)
    }
  }

  fun refreshData() {
    repeatSource.onNext(Unit)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }
}
