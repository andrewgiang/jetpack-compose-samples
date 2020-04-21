package com.detroitlabs.composeplayground.expenseapp.ui

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.ExtendedFloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.expenseapp.Async
import com.detroitlabs.composeplayground.expenseapp.UiModel
import com.detroitlabs.composeplayground.expenseapp.api.FakeAPI
import com.detroitlabs.composeplayground.ui.SampleTheme
import com.detroitlabs.composeplayground.ui.SwipeToRefreshLayout

@Composable
fun ExpenseHomeScreen(uiState: Async<UiModel>, onRefresh: () -> Unit) {
  // DEMO: Go over Material Theme and customization
  SampleTheme {
    // Scaffold implements the basic material design visual layout structure.
    // i.e some toolbar, drawer layout, floating action buttons, body content
    Scaffold(
        topAppBar = { AppBar() },
        floatingActionButton = { AddExpenseButton() },
        bodyContent = {
          // Hacky swipe to refresh layout will be replaced later on
          SwipeToRefreshLayout(
              refreshState = uiState == Async.Loading,
              onRefresh = onRefresh,
              swipeIcon = { RefreshIcon() }
          ) {
            // DEMO: Review concept of AsyncConcept
            AsyncContent(
                uiState,
                onData = { model -> ExpenseContent(model) },
                onError = {
                  Box(Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
                    Text(text = "Unexpected Error")
                  }
                }
            )
          }
        }
    )
  }
}

@Composable
fun AppBar() {
  TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
    Text(text = "Money",
        modifier = Modifier.gravity(Alignment.CenterVertically) + Modifier.padding(16.dp),
        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
    )
  }
}

@Composable
fun AddExpenseButton() {
  // DEMO: Demo context ambient usage
  val context = ContextAmbient.current
  return ExtendedFloatingActionButton(
      text = { Text(text = "Add Expense") },
      onClick = {
        Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show()
      })
}

@Composable
fun ExpenseContent(value: UiModel) {
  val (profile, expenses) = value
  Column {
    BalanceInfo(profile.balance)
    Spacer(modifier = Modifier.height(4.dp))
    ExpenseList(expenses = expenses)
  }
}

// DEMO: Previews
@Preview("Data Preview")
@Composable
fun DataPreview() {
  val data = FakeAPI.profile to FakeAPI.expenses
  ExpenseHomeScreen(uiState = Async.Data(data), onRefresh = {})
}

@Preview("Error Preview")
@Composable
fun ErrorPreview() {
  ExpenseHomeScreen(uiState = Async.Error(Exception()), onRefresh = {})
}