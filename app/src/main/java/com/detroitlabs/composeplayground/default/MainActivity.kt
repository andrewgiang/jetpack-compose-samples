package com.detroitlabs.composeplayground.default

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.rxjava.RxJavaActivity

data class Sample(val name: String, val intent: Intent)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sampleScreens = listOf(
            Sample("Rx Sample", Intent(this, RxJavaActivity::class.java))
        )
        setContent {
            MaterialTheme {
                SampleScreenList(sampleScreens)
            }
        }
    }
}

@Composable
fun SampleScreenList(samples: List<Sample>) {
    AdapterList(data = samples, modifier = Modifier.fillMaxSize() + Modifier.padding(16.dp)) {
        val context = ContextAmbient.current
        Clickable(onClick = { context.startActivity(it.intent) }) {
            Text(text = it.name)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        SampleScreenList(listOf(Sample("Sample", Intent())))
    }
}