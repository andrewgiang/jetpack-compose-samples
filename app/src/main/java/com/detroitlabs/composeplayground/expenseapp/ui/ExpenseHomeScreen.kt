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
  SampleTheme {
    Scaffold(
        topAppBar = { AppBar() },
        floatingActionButton = { AddExpenseButton() },
        bodyContent = {
          SwipeToRefreshLayout(
              refreshState = uiState == Async.Loading,
              onRefresh = onRefresh,
              swipeIcon = { RefreshIcon() }
          ) {
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
  Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
    BalanceInfo(profile.balance)
    Spacer(modifier = Modifier.height(4.dp))
    ExpenseList(expenses = expenses)
  }
}

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