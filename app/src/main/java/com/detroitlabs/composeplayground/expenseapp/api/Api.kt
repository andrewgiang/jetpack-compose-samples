package com.detroitlabs.composeplayground.expenseapp.api

import com.detroitlabs.composeplayground.expenseapp.model.Expense
import com.detroitlabs.composeplayground.expenseapp.model.ExpenseType
import com.detroitlabs.composeplayground.expenseapp.model.Profile
import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface Api {
  fun getExpenses(): Single<List<Expense>>
  fun getProfile(): Single<Profile>
}

object FakeAPI : Api {
  val expenses = listOf(
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00"),
      Expense(ExpenseType.COMPANY_BENEFIT, "Krisp", "30 Mar 2020", "$40.00"),
      Expense(ExpenseType.TAXABLE_INCOME, "Amazon", "05 Nov 2019", "$25.00")
  )

  val profile = Profile("$1500.00")

  override fun getExpenses(): Single<List<Expense>> {
    return Single.just(expenses).delay(3, TimeUnit.SECONDS)
  }

  override fun getProfile(): Single<Profile> {
    return Single.just(profile).delay(3, TimeUnit.SECONDS)
  }
}
