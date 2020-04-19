package com.detroitlabs.composeplayground.ui

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette
import androidx.ui.res.colorResource
import com.detroitlabs.composeplayground.R

object AppColors {
  private val primaryColor: Color = Color(0xFFfdc800)
  private val primaryVariant: Color = Color(0xFF000000)

  val lightPalette: ColorPalette = lightColorPalette(
      primary = primaryColor,
      primaryVariant = primaryVariant
  )
  val darkPalette: ColorPalette = darkColorPalette(
      primary = primaryColor,
      primaryVariant = primaryVariant
  )
  @Composable
  val currentPalette: ColorPalette
    get() = if (isSystemInDarkTheme()) darkPalette else lightPalette
}

@Composable
fun SampleTheme(content: @Composable() () -> Unit) {
  MaterialTheme(colors = AppColors.currentPalette) {
    content()
  }
}