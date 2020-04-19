package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.layout.Column
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.detroitlabs.composeplayground.R
import com.detroitlabs.composeplayground.expenseapp.model.ExpenseType

@Composable
fun ExpenseIcon(modifier: Modifier = Modifier, expenseType: ExpenseType) {
  val iconId = when (expenseType) {
    ExpenseType.TAXABLE_INCOME -> R.drawable.ic_company_benefit
    ExpenseType.COMPANY_BENEFIT -> R.drawable.ic_taxable_expense
  }
  Icon(modifier = modifier, asset = vectorResource(id = iconId))
}

@Preview
@Composable
fun IconPreview() {
  Column {
    ExpenseIcon(expenseType = ExpenseType.COMPANY_BENEFIT)
    ExpenseIcon(expenseType = ExpenseType.TAXABLE_INCOME)
  }
}