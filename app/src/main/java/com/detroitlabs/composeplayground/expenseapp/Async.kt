package com.detroitlabs.composeplayground.expenseapp

sealed class Async<out T : Any> {
  object Loading : Async<Nothing>()
  data class Data<T : Any>(val data: T) : Async<T>()
  data class Error(val throwable: Throwable) : Async<Nothing>()
}