package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.expenseapp.Expense
import com.detroitlabs.composeplayground.expenseapp.FakeAPI

@Composable
fun ExpenseList(expenses: List<Expense>) {
  Column {
    SubmittedSubHeader()
    AdapterList(data = expenses) {
      ExpenseItem(it)
    }
  }
}

@Composable
fun ExpenseItem(it: Expense) {
  Row(modifier = Modifier.preferredHeight(71.dp)) {
    ExpenseIcon(it.expenseType)
    Column(modifier = Modifier.padding(start = 16.dp) + Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
      Text(text = it.businessName)
      Text(text = it.date)
    }
  }
}

@Composable
fun SubmittedSubHeader() {
  Spacer(modifier = Modifier.height(24.dp))
  Box(backgroundColor = MaterialTheme.colors.primary) {
    Text(
        text = " SUBMITTED FOR REIMBURSEMENT ",
        color = MaterialTheme.colors.surface,
        style = MaterialTheme.typography.overline
    )
  }
}

@Preview
@Composable
fun ExpenseListPreview() {
  val expenses = FakeAPI.expenses
  ExpenseAppTheme {
    Box(Modifier.drawBackground(color = Color.White)) {
      ExpenseList(expenses = expenses)
    }
  }
}