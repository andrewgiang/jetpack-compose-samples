package com.detroitlabs.composeplayground.counter

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.unit.dp

@Composable
fun CounterItem(value: Int, onClick: () -> Unit = {}) {
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = "Count Value: $value")
    Button(onClick = onClick) {
      Text(text = "Increment Count")
    }
  }
}