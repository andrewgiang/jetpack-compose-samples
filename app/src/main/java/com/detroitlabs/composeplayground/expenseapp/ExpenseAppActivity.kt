package com.detroitlabs.composeplayground.expenseapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import com.detroitlabs.composeplayground.expenseapp.ui.ExpenseApp

class ExpenseAppActivity : AppCompatActivity() {

  private val viewModel: ExpenseViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ExpenseApp(viewModel)
    }
  }
}
