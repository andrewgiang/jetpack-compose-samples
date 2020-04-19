package com.detroitlabs.composeplayground.counter_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import com.detroitlabs.composeplayground.ui.SampleTheme
import io.reactivex.subjects.BehaviorSubject

class BasicCounterSample : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SampleTheme {
        Column {
          Text(text = "State Sample")
          CounterStateSample()

          Text(text = "Model Sample")
          CounterModelSample()

          Text("RxJava Sample")
          val data = BehaviorSubject.createDefault(0)
          RxJavaCounter(data)

          Text("LiveData Sample")
          val liveData = MutableLiveData(0)
          LiveDataCounter(liveData)
        }
      }
    }
  }
}