package com.detroitlabs.composeplayground.counter_sample

import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.unit.dp

@Composable
fun CounterStateSample() {
  val (value, setCount) = state { 0 }
  CounterItem(value = value, onClick = { setCount(value + 1) })
}

@Model
object Counter {
  var count = 0
}

@Composable
fun CounterModelSample() {
  CounterItem(value = Counter.count, onClick = { Counter.count++ })
}

@Composable
fun CounterItem(value: Int, onClick: () -> Unit = {}) {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Count Value: $value")
    Button(onClick = onClick) {
      Text(text = "Increment Count")
    }
  }
}