package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.unit.dp

@Composable
fun RefreshIcon() {
  Box(
      modifier = Modifier.fillMaxWidth(),
      gravity = ContentGravity.Center
  ) {
    Box(
        modifier = Modifier.drawShadow(shape = CircleShape, elevation = 4.dp),
        backgroundColor = Color.White
    ) {
      CircularProgressIndicator(
          modifier = Modifier.padding(
              4.dp
          )
      )
    }
  }
}