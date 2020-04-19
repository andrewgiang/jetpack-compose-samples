package com.detroitlabs.composeplayground.counter.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import androidx.ui.material.MaterialTheme
import com.detroitlabs.composeplayground.counter.CounterItem

class LiveDataCounterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = MutableLiveData(0)
        setContent {
            MaterialTheme {
                val state = data.observeAsState(initial = 0)
                CounterItem(value = state.value, onClick = {
                    data.value = data.value?.plus(1)
                })
            }
        }
    }
}
