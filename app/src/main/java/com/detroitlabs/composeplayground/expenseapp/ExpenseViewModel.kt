package com.detroitlabs.composeplayground.expenseapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

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
    return api.getProfile()
        .zipWith(
            api.getExpenses(),
            BiFunction<Profile, List<Expense>, Async<UiModel>> { profile, expenses ->
              Async.Data(profile to expenses)
            }).toObservable()
        .startWith(Async.Loading)
        .onErrorReturn { error ->
          Async.Error(error)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .repeatWhen { repeatSource }
  }

  fun refreshData() {
    repeatSource.onNext(Unit)
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }

}

interface Api {
  fun getExpenses(): Single<List<Expense>>
  fun getProfile(): Single<Profile>
}

data class Expense(
    val expenseType: ExpenseType,
    val businessName: String,
    val date: String,
    val amount: String
)

data class Profile(val balance: String)

enum class ExpenseType {
  TAXABLE_INCOME,
  COMPANY_BENEFIT
}

object FakeAPI : Api {
  val expenses = listOf(
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00")
  )

  val profile = Profile("$1500.00")

  override fun getExpenses(): Single<List<Expense>> {
    return Single.just(expenses).delay(3, TimeUnit.SECONDS)
  }

  override fun getProfile(): Single<Profile> {
    return Single.just(profile).delay(3, TimeUnit.SECONDS)
  }
}


sealed class Async<out T : Any> {
  object Loading : Async<Nothing>()
  data class Data<T : Any>(val data: T) : Async<T>()
  data class Error(val throwable: Throwable) : Async<Nothing>()
}