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
  Column(modifier = Modifier.fillMaxWidth()) {
    SubmittedSubHeader()
    //DEMO: Talk about AdapterList
    AdapterList(data = expenses) {
      ExpenseListItem(it)
    }
  }
}

//DEMO: Talk about weight modifier within RowScope
@Composable
fun ExpenseListItem(expense: Expense) {
  Row(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
    val alignmentModifier = Modifier.gravity(Alignment.CenterVertically)
    ExpenseIcon(
        modifier = alignmentModifier + Modifier.weight(1f),
        expenseType = expense.expenseType
    )
    Column(
        modifier = alignmentModifier + Modifier.weight(4f),
        verticalArrangement = Arrangement.Center
    ) {
      Text(text = expense.businessName)
      Text(text = expense.date, style = MaterialTheme.typography.caption)
    }
    Text(
        text = expense.amount,
        modifier = alignmentModifier + Modifier.weight(1f)
    )
  }
}

@Composable
fun SubmittedSubHeader() {
  Box(
      modifier = Modifier.padding(16.dp),
      padding = 2.dp,
      backgroundColor = MaterialTheme.colors.onSurface
  ) {
    Text(
        text = "SUBMITTED FOR REIMBURSEMENT",
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