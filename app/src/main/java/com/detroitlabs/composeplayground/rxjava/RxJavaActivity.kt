package com.detroitlabs.composeplayground.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.rxjava2.subscribeAsState
import io.reactivex.subjects.BehaviorSubject

class RxJavaActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val data = BehaviorSubject.createDefault("initial")
    setContent {
      MaterialTheme {
        val state = data.subscribeAsState(initial = "")
        Column {
          Text(text = state.value)
          Button(onClick = { data.onNext("Doing Something") }) {
            Text(text = "Do Something")
          }
        }
      }
    }
  }
}
