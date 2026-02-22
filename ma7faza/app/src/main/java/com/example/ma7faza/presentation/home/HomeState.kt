package com.example.ma7faza.presentation.home

import com.example.ma7faza.domain.data.TransactionItem

data class HomeState (
    val transferClicked: Boolean = false,
    val historyClicked: Boolean = false,
    val profileClicked: Boolean = false,
    val balance: String = "Balance",
    val isbalanceVisible: Boolean = true,
    val name: String = "Name",
    val transactions: List<TransactionItem> = emptyList(),
    val isLoading: Boolean = false
)
