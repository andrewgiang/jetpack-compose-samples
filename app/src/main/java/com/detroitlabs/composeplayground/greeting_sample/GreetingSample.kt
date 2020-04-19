package com.detroitlabs.composeplayground.greeting_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.tooling.preview.Preview

class GreetingSample : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Greeting(name = "Android Group Hug")
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name")
}

@Preview(showDecoration = true)
@Composable
fun GreetingPreviewOnDevice() {
  Greeting("Test Preview")
}
@Preview()
@Composable
fun GreetingPreview() {
  Greeting("Test Preview")
}