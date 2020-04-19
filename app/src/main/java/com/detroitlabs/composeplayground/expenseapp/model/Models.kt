package com.detroitlabs.composeplayground.expenseapp.model

data class Expense(
    val expenseType: ExpenseType,
    val businessName: String,
    val date: String,
    val amount: String
)

data class Profile(val balance: String)

enum class ExpenseType {
  TAXABLE_INCOME,
  COMPANY_BENEFIT
}