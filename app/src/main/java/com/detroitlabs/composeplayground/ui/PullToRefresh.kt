package com.detroitlabs.composeplayground.ui

import androidx.animation.TweenBuilder
import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.layout.Stack
import androidx.ui.layout.offset
import androidx.ui.unit.dp
import androidx.ui.unit.px
import com.detroitlabs.composeplayground.ui.internal.StateDraggable

@Composable
fun SwipeToRefreshLayout(
    refreshState: Boolean,
    onRefresh: () -> Unit,
    swipeIcon: @Composable() () -> Unit,
    content: @Composable() () -> Unit
) {
  val size = with(DensityAmbient.current) { 100.dp.toPx().value }
  //min is below - to hide
  val min = -size *2.5f
  val max = size * 2f
  StateDraggable(
      state = refreshState,
      onStateChange = { if (it) onRefresh() },
      anchorsToState = listOf(min to false, size to true),
      animationBuilder = TweenBuilder(),
      dragDirection = DragDirection.Vertical,
      minValue = min,
      maxValue = max
  ) { value ->
    val dpOffset = with(DensityAmbient.current) {
      (value.value * 0.5).px.toDp()
    }
    Stack {
      content()
      Box(modifier = Modifier.offset(0.dp, dpOffset), gravity = ContentGravity.TopCenter) {
        swipeIcon()
      }
    }
  }
}
