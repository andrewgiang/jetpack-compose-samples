package com.detroitlabs.composeplayground.expenseapp.ui

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Border
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

@Composable
fun BalanceInfo(balance: String) {
  Box(modifier = Modifier.fillMaxWidth(),
      border = Border(1.dp, color = MaterialTheme.colors.primary),
      shape = RoundedCornerShape(4.dp)
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "Account Balance", style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.ExtraBold))
      Text(text = balance, style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.ExtraBold))
    }
  }
}

@Preview
@Composable
fun DefaultPreview() {
  ExpenseAppTheme {
    BalanceInfo(balance = "$1235.00")
  }
}