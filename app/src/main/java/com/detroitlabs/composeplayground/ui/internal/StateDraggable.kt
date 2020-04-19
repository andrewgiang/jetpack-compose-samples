package com.detroitlabs.composeplayground.ui.internal

import androidx.animation.AnimatedFloat
import androidx.animation.AnimationBuilder
import androidx.animation.AnimationEndReason
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.animation.animatedFloat
import androidx.ui.core.Modifier
import androidx.ui.core.PassThroughLayout
import androidx.ui.foundation.animation.AnchorsFlingConfig
import androidx.ui.foundation.animation.fling
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.gestures.draggable

@Composable
internal fun <T> StateDraggable(
    state: T,
    onStateChange: (T) -> Unit,
    anchorsToState: List<Pair<Float, T>>,
    animationBuilder: AnimationBuilder<Float>,
    dragDirection: DragDirection,
    enabled: Boolean = true,
    minValue: Float = Float.MIN_VALUE,
    maxValue: Float = Float.MAX_VALUE,
    content: @Composable() (AnimatedFloat) -> Unit
) {
  val forceAnimationCheck = state { true }

  val anchors = remember(anchorsToState) { anchorsToState.map { it.first } }
  val currentValue = anchorsToState.firstOrNull { it.second == state }!!.first
  val flingConfig =
      AnchorsFlingConfig(anchors, animationBuilder, onAnimationEnd = { reason, finalValue, _ ->
        if (reason != AnimationEndReason.Interrupted) {
          val newState = anchorsToState.firstOrNull { it.first == finalValue }?.second
          if (newState != null && newState != state) {
            onStateChange(newState)
            forceAnimationCheck.value = !forceAnimationCheck.value
          }
        }
      })
  val position = animatedFloat(currentValue)
  position.setBounds(minValue, maxValue)

  // This state is to force this component to be recomposed and trigger onCommit below
  // This is needed to stay in sync with drag state that caller side holds
  onCommit(currentValue, forceAnimationCheck.value) {
    position.animateTo(currentValue, animationBuilder)
  }
  val draggable = Modifier.draggable(
      dragDirection = dragDirection,
      onDragDeltaConsumptionRequested = { delta ->
        val old = position.value
        position.snapTo(position.value + delta)
        position.value - old
      },
      onDragStopped = { position.fling(flingConfig, it) },
      enabled = enabled,
      startDragImmediately = position.isRunning
  )
  // TODO(b/150706555): This layout is temporary and should be removed once Semantics
  //  is implemented with modifiers.
  @Suppress("DEPRECATION")
  PassThroughLayout(draggable) {
    content(position)
  }
}