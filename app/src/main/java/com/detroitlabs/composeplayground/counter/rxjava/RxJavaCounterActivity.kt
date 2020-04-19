package com.detroitlabs.composeplayground.counter.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.rxjava2.subscribeAsState
import com.detroitlabs.composeplayground.counter.CounterItem
import io.reactivex.subjects.BehaviorSubject

class RxJavaCounterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = BehaviorSubject.createDefault(0)
        setContent {
            MaterialTheme {
                val state = data.subscribeAsState(initial = 0)
                CounterItem(value = state.value, onClick = {
                    data.value?.plus(1)?.let { incrementedValue ->
                        data.onNext(incrementedValue)
                    }
                })
            }
        }
    }
}

