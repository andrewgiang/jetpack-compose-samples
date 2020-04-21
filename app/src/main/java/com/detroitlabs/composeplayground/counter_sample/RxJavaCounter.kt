package com.detroitlabs.composeplayground.counter_sample

import androidx.compose.Composable
import androidx.ui.rxjava2.subscribeAsState
import io.reactivex.subjects.BehaviorSubject

@Composable
fun RxJavaCounter(data: BehaviorSubject<Int>) {
  val state = data.subscribeAsState(initial = 0)
  CounterItem(
    value = state.value,
    onClick = {
      data.onNext(state.value +1)
    })
}