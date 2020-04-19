package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.detroitlabs.composeplayground.expenseapp.api.FakeAPI
import com.detroitlabs.composeplayground.expenseapp.model.Expense
import com.detroitlabs.composeplayground.ui.SampleTheme

@Composable
fun ExpenseList(expenses: List<Expense>) {
  Column {
    SubmittedSubHeader()
    AdapterList(data = expenses) {
      ExpenseListItem(it)
    }
  }
}

@Composable
fun ExpenseListItem(expense: Expense) {
  Row(modifier = Modifier.preferredHeight(71.dp)) {
    ExpenseIcon(modifier = Modifier.gravity(Alignment.CenterVertically), expenseType = expense.expenseType)
    Column(modifier = Modifier.padding(start = 16.dp) + Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
      Text(text = expense.businessName)
      Text(text = expense.date, style = MaterialTheme.typography.caption)
    }
  }
}

@Composable
fun SubmittedSubHeader() {
  Spacer(modifier = Modifier.height(24.dp))
  Box(backgroundColor = MaterialTheme.colors.onSurface) {
    Text(
        text = " SUBMITTED FOR REIMBURSEMENT ",
        color = MaterialTheme.colors.surface,
        style = MaterialTheme.typography.overline
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ExpenseListPreview() {
  val expenses = FakeAPI.expenses
  SampleTheme {
    ExpenseList(expenses = expenses)
  }
}