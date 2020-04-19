package com.detroitlabs.composeplayground.expenseapp.ui

import android.widget.Toast
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.height
import androidx.ui.layout.padding
import androidx.ui.livedata.observeAsState
import androidx.ui.material.ExtendedFloatingActionButton
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.expenseapp.Async
import com.detroitlabs.composeplayground.expenseapp.ExpenseViewModel
import com.detroitlabs.composeplayground.expenseapp.UiModel
import com.detroitlabs.composeplayground.ui.SampleTheme
import com.detroitlabs.composeplayground.ui.SwipeToRefreshLayout

@Composable
fun ExpenseApp(viewModel: ExpenseViewModel) {
  val uiState = viewModel.uiModel.observeAsState(initial = Async.Loading)
  SampleTheme {
    Scaffold(
        topAppBar = { AppBar() },
        floatingActionButton = { AddExpenseButton() },
        bodyContent = {
          SwipeToRefreshLayout(
              refreshState = uiState.value == Async.Loading,
              onRefresh = { viewModel.refreshData() },
              swipeIcon = { RefreshIcon() }
          ) {
            AsyncContent(
                uiState.value,
                onData = { model -> ExpenseHomeScreen(model) },
                onError = { Text(text = "Unexpected Error") }
            )
          }
        }
    )
  }
}

@Composable
fun AppBar() {
  Box(modifier = Modifier.height(48.dp) + Modifier.padding(16.dp), gravity = ContentGravity.Center) {
    Text(text = "Money", style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold))
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
fun ExpenseHomeScreen(value: UiModel) {
  val (profile, expenses) = value
  Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
    BalanceInfo(profile.balance)
    Spacer(modifier = Modifier.height(4.dp))
    ExpenseList(expenses = expenses)
  }
}