package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.lightColorPalette
import androidx.ui.res.colorResource
import com.detroitlabs.composeplayground.R


@Composable
fun ExpenseAppTheme(content: @Composable() () -> Unit) {
  val black = Color.Black
  val marigold = colorResource(id = R.color.marigold)
  val colors = lightColorPalette(primary = black, secondary = marigold)
  MaterialTheme(colors = colors) {
    content()
  }
}