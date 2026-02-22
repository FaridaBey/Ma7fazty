package com.example.ma7faza.presentation.history

import com.example.ma7faza.domain.data.TransactionFilter
import com.example.ma7faza.domain.data.TransactionItem

sealed class HistoryIntent {
    data class OnFilterChange(val filter: TransactionFilter) : HistoryIntent()
    data class OnSearchQueryChange(val query: String) : HistoryIntent()
    object OnClearSearch : HistoryIntent()
    object OnHomeClick : HistoryIntent()
    object OnTransferClick : HistoryIntent()
    object OnProfileClick : HistoryIntent()
    object LoadHistory : HistoryIntent()
    data class OnTransactionClick(val transaction: TransactionItem) : HistoryIntent()
    object DismissDialog : HistoryIntent()
}
