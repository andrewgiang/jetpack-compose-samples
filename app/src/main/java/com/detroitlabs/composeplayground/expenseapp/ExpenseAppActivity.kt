package com.detroitlabs.composeplayground.expenseapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
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
import com.detroitlabs.composeplayground.ui.SwipeToRefreshLayout
import com.detroitlabs.composeplayground.expenseapp.ui.BalanceInfo
import com.detroitlabs.composeplayground.expenseapp.ui.ExpenseAppTheme
import com.detroitlabs.composeplayground.expenseapp.ui.ExpenseList
import com.detroitlabs.composeplayground.expenseapp.ui.RefreshIcon


class ExpenseAppActivity : AppCompatActivity() {

  private val viewModel: ExpenseViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ExpenseApp(viewModel)
    }
  }
}

@Composable
fun ExpenseApp(viewModel: ExpenseViewModel) {
  val uiState = viewModel.uiModel.observeAsState(initial = Async.Loading)
  ExpenseAppTheme {
    Scaffold(
        topAppBar = { AppBar() },
        floatingActionButton = { AddExpenseButton() },
        bodyContent = {
          SwipeToRefreshLayout(
              refreshState = uiState.value == Async.Loading,
              onRefresh = { viewModel.refreshData() },
              swipeIcon = { RefreshIcon() }
          ) {
            Body(uiState.value)
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
      },
      backgroundColor = MaterialTheme.colors.secondary)
}


@Composable
private fun Body(uiState: Async<UiModel>) {
  when (uiState) {
    is Async.Data -> {
      val data = uiState.data
      Content(data)
    }
    is Async.Error -> Text(text = "error")
  }
}

@Composable
fun Content(value: UiModel) {
  val (profile, expenses) = value
  Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
    BalanceInfo(profile.balance)
    Spacer(modifier = Modifier.height(4.dp))
    ExpenseList(expenses = expenses)
  }
}

