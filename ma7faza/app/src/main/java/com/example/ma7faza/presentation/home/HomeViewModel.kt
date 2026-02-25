package com.example.ma7faza.presentation.home

import androidx.lifecycle.ViewModel
import com.example.ma7faza.R
import com.example.ma7faza.domain.data.TransactionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class homeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.TransferClicked -> gotoTransfer()
            is HomeIntent.HistoryClicked -> gotoHistory()
            is HomeIntent.ProfileClicked -> gotoProfile()
            is HomeIntent.ToggleBalanceVisibility -> toggleBalanceVisibility()
            is HomeIntent.LoadHome -> loadHomeData()
        }
    }

    private fun gotoTransfer() {
        _state.value = _state.value.copy(transferClicked = true)
    }

    private fun gotoHistory() {
        _state.value = _state.value.copy(historyClicked = true)
    }

    private fun gotoProfile() {
        _state.value = _state.value.copy(profileClicked = true)
    }

    private fun toggleBalanceVisibility() {
        _state.value = _state.value.copy(
            isbalanceVisible = !_state.value.isbalanceVisible
        )
    }
    private fun loadHomeData() {
        _state.value = _state.value.copy(isLoading = true)
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
            )
        )

        _state.value = _state.value.copy(transactions = mockTransactions.subList(0,2),
            isLoading = false)
    }
}
