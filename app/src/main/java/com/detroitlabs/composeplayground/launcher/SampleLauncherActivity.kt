package com.detroitlabs.composeplayground.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.counter.livedata.LiveDataCounterActivity
import com.detroitlabs.composeplayground.counter.rxjava.RxJavaCounterActivity
import com.detroitlabs.composeplayground.expenseapp.ExpenseAppActivity
import com.detroitlabs.composeplayground.ui.SampleTheme

data class Sample(val name: String, val intent: Intent)

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val sampleScreens = listOf(
        Sample("Rx Counter Sample", Intent(this, RxJavaCounterActivity::class.java)),
        Sample("LiveData Counter Sample", Intent(this, LiveDataCounterActivity::class.java)),
        Sample("Expense App Sample", Intent(this, ExpenseAppActivity::class.java))
    )
    setContent {
      SampleTheme {
        SampleScreenList(sampleScreens)
      }
    }
  }
}

@Composable
fun SampleScreenList(samples: List<Sample>) {
  AdapterList(data = samples,
      modifier = Modifier.drawBackground(color = MaterialTheme.colors.surface) +
          Modifier.fillMaxSize() +
          Modifier.padding(16.dp)) {
    val context = ContextAmbient.current
    Button(
        onClick = { context.startActivity(it.intent) },
        modifier = Modifier.fillMaxWidth() + Modifier.padding(8.dp)
    ) {
      Text(text = it.name)
    }
  }
}

@Preview
@Composable
fun DefaultPreview() {
  SampleTheme {
    SampleScreenList(listOf(Sample("Sample", Intent())))
  }
}