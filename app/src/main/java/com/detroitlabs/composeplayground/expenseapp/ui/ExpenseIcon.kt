package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.foundation.Icon
import androidx.ui.layout.fillMaxHeight
import androidx.ui.res.vectorResource
import com.detroitlabs.composeplayground.R
import com.detroitlabs.composeplayground.expenseapp.model.ExpenseType

@Composable
fun ExpenseIcon(expenseType: ExpenseType) {
  val iconId = when (expenseType) {
    ExpenseType.TAXABLE_INCOME -> R.drawable.ic_company_benefit
    ExpenseType.COMPANY_BENEFIT -> R.drawable.ic_taxable_expense
  }
  Box(modifier = Modifier.fillMaxHeight(), gravity = ContentGravity.Center) {
    Icon(asset = vectorResource(id = iconId))
  }
}

