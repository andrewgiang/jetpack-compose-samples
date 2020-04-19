package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.ui.SampleTheme

@Composable
fun RefreshIcon() {
  Box(Modifier.fillMaxWidth(),
      gravity = ContentGravity.Center
  ) {
    Surface(shape = CircleShape,
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
      CircularProgressIndicator(Modifier.padding(4.dp))
    }
  }
}

@Composable
@Preview
fun RefreshIconPreview() {
  SampleTheme {
    RefreshIcon()
  }
}