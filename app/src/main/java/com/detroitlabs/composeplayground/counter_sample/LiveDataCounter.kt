package com.detroitlabs.composeplayground.counter_sample

import androidx.compose.Composable
import androidx.lifecycle.MutableLiveData
import androidx.ui.livedata.observeAsState

@Composable
fun LiveDataCounter(liveData: MutableLiveData<Int>) {
  val state = liveData.observeAsState(initial = 0)
  CounterItem(
    value = state.value,
    onClick = {
      liveData.value = liveData.value?.plus(1)
    })
}