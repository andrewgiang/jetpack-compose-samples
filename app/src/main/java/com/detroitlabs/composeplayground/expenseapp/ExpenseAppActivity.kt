package com.detroitlabs.composeplayground.expenseapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.livedata.observeAsState
import com.detroitlabs.composeplayground.expenseapp.ui.ExpenseHomeScreen

class ExpenseAppActivity : AppCompatActivity() {

  private val viewModel: ExpenseViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val uiState = viewModel.uiModel.observeAsState(initial = Async.Loading)
      ExpenseHomeScreen(uiState.value, onRefresh = { viewModel.refreshData() })
    }
  }
}
