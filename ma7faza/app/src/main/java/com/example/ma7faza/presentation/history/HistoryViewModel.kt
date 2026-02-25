package com.example.ma7faza.presentation.history

import androidx.lifecycle.ViewModel
import com.example.ma7faza.R
import com.example.ma7faza.domain.data.TransactionFilter
import com.example.ma7faza.domain.data.TransactionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class historyViewModel : ViewModel() {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()


    fun processIntent(intent: HistoryIntent) {
        when (intent) {
            is HistoryIntent.OnHomeClick -> gotoHome()
            is HistoryIntent.OnTransferClick -> gotoTransfer()
            is HistoryIntent.OnProfileClick -> gotoProfile()
            is HistoryIntent.LoadHistory -> loadHistoryData()
            is HistoryIntent.OnSearchQueryChange -> {
                updateSearchQuery(intent.query)
            }

            is HistoryIntent.OnClearSearch -> {
                updateSearchQuery("")
            }

            is HistoryIntent.OnFilterChange -> {
                updateFilter(intent.filter)
            }
            is HistoryIntent.OnTransactionClick -> {
                _state.value = _state.value.copy(
                    selectedTransaction = intent.transaction,
                    isDialogVisible = true
                )
            }
            is HistoryIntent.DismissDialog -> {
                _state.value = _state.value.copy(
                    isDialogVisible = false
                    // Note: We keep selectedTransaction so it doesn't
                    // disappear instantly during the dismiss animation
                )
            }
        }

        }
    private fun updateFilter(filter: TransactionFilter) {
        val allTransactions = _state.value.transactions
        val filtered = when (filter) {
            TransactionFilter.ALL -> allTransactions
            TransactionFilter.INCOME -> allTransactions.filter { it.isCredit }
            TransactionFilter.EXPENSE -> allTransactions.filter { !it.isCredit }
        }
        _state.value = _state.value.copy(
            selectedFilter = filter,
            filteredTransactions = filtered
        )
    }
    private fun updateSearchQuery(query: String) {
        val allTransactions = _state.value.transactions

        // Simple filter logic: search by name
        val filtered = if (query.isEmpty()) {
            allTransactions
        } else {
            allTransactions.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }

        _state.value = _state.value.copy(
            query = query,
            filteredTransactions = filtered
        )
    }
    private fun gotoProfile(){
        _state.value = _state.value.copy(profileClicked = true)
    }
    private fun gotoTransfer(){
        _state.value = _state.value.copy(transferClicked = true)
    }
    private fun gotoHome(){
        _state.value = _state.value.copy(homeClicked = true)
    }
    private fun loadHistoryData() {
        _state.value = _state.value.copy(isloading = true)
        // Mock data for now
        val mockTransactions = listOf(
            TransactionItem(
                id = 1,
                name = "Walmart",
                date = "Today 12:42",
                amount = "35.23",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Success"
            ),
            TransactionItem(
                id = 2,
                name = "Salary",
                date = "Today 12:32",
                amount = "2500.00",
                icon = R.drawable.ic_transfer,
                isCredit = true,
                Status = "Success"
            ),
            TransactionItem(
                id = 3,
                name = "Amazon",
                date = "Yesterday 10:15",
                amount = "120.50",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Failed"
            ),
            TransactionItem(
                id = 4,
                name = "Walmart",
                date = "Today 12:42",
                amount = "35.23",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Pending"
            ),
            TransactionItem(
                id = 5,
                name = "Salary",
                date = "Today 12:32",
                amount = "2500.00",
                icon = R.drawable.ic_transfer,
                isCredit = true,
                Status = "Success"
            ),
            TransactionItem(
                id = 6,
                name = "Amazon",
                date = "Yesterday 10:15",
                amount = "120.50",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Failed"
            ),
            TransactionItem(
                id = 7,
                name = "Walmart",
                date = "Today 12:42",
                amount = "35.23",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Success"
            ),
            TransactionItem(
                id = 8,
                name = "Salary",
                date = "Today 12:32",
                amount = "2500.00",
                icon = R.drawable.ic_transfer,
                isCredit = true,
                Status = "Success"
            ),
            TransactionItem(
                id = 9,
                name = "Amazon",
                date = "Yesterday 10:15",
                amount = "120.50",
                icon = R.drawable.ic_transfer,
                isCredit = false,
                Status = "Pending"
            )
        )

        _state.value = _state.value.copy(
            transactions = mockTransactions,
            filteredTransactions = mockTransactions,
            isloading = false)
    }






}