package com.example.ma7faza.presentation.history

import com.example.ma7faza.domain.data.TransactionFilter
import com.example.ma7faza.domain.data.TransactionItem

data class HistoryState(
    val transactions: List<TransactionItem> = emptyList(),
    val transferClicked: Boolean = false,
    val homeClicked: Boolean = false,
    val profileClicked: Boolean = false,
    val isloading: Boolean = false,
    val query: String = "",
    val filteredTransactions: List<TransactionItem> = emptyList(),
    val selectedFilter: TransactionFilter = TransactionFilter.ALL,
    val isDialogVisible: Boolean = false,
    val selectedTransaction: TransactionItem? = null
)
