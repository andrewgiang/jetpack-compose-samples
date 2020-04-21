package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import com.detroitlabs.composeplayground.expenseapp.Async

// DEMO: Discuss reusable constructs based on data models
@Composable
fun <T : Any> AsyncContent(
    uiState: Async<T>,
    onData: @Composable() (T) -> Unit,
    onError: @Composable() () -> Unit
) {
  when (uiState) {
    is Async.Data -> onData(uiState.data)
    is Async.Error -> onError()
  }
}