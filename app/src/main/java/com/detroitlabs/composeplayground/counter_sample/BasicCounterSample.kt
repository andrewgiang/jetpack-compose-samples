package com.detroitlabs.composeplayground.counter_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.ui.SampleTheme
import io.reactivex.subjects.BehaviorSubject

class BasicCounterSample : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SampleTheme {
        val headerStyle = MaterialTheme.typography.h6
        Column(modifier = Modifier.padding(16.dp)) {
          Text(text = "State Sample", style = headerStyle)
          CounterStateSample()

          Text(text = "@Model Sample", style = headerStyle)
          CounterModelSample()

          Text("RxJava Sample", style =headerStyle)
          val data = BehaviorSubject.createDefault(0)
          RxJavaCounter(data)

          Text("LiveData Sample", style = headerStyle)
          val liveData = MutableLiveData(0)
          LiveDataCounter(liveData)
        }
      }
    }
  }
}