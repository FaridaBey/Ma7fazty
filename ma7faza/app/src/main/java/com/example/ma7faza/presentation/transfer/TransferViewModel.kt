package com.example.ma7faza.presentation.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class transferViewModel : ViewModel() {
    private val _state = MutableStateFlow(TransferState())
    val state: StateFlow<TransferState> = _state.asStateFlow()

    fun processIntent(intent: TransferIntent) {
        when (intent) {
            is TransferIntent.OnHomeClick -> gotoHome()
            is TransferIntent.OnProfileClick -> gotoProfile()
            is TransferIntent.OnHistoryClick -> gotoHistory()
            is TransferIntent.OnAmountChange -> updateAmount(intent.amount)
            is TransferIntent.OnTransferClick -> transfer()
            is TransferIntent.OnTransferSuccess -> transferSuccess()
            is TransferIntent.OnTransferError -> {}
            is TransferIntent.OnRecipientNumberChange -> updateRecipientNumber(intent.number)
        }
    }

    private fun gotoProfile() {
        _state.value = _state.value.copy(profileClicked = true)
    }

    private fun gotoHistory() {
        _state.value = _state.value.copy(historyClicked = true)
    }

    private fun gotoHome() {
        _state.value = _state.value.copy(homeClicked = true)
    }

    private fun updateAmount(amount: String) {
        _state.value = _state.value.copy(amount = amount, amountError = null)
    }

    private fun updateRecipientNumber(number: String) {
        _state.value = _state.value.copy(recipientNumber = number, recipientNumberError = null)
    }

    private fun isValidPhoneNumber(number: String): Boolean {
        val regex = Regex("^01[0|1|2|5]\\d{8}$")
        return regex.matches(number)
    }

    // formating for amount

    private fun validateTransfer(): Boolean {

        val recipientNumber = _state.value.recipientNumber.trim()
        val recipientError = if (recipientNumber.isEmpty()) {
            "Recipient number required"
        } else if (!isValidPhoneNumber(recipientNumber)) {
            "Invalid phone number"
        } else {
            null
        }

        _state.value = _state.value.copy(
            recipientNumberError = recipientError
        )

        return recipientError == null
    }

    private fun transfer() {
        viewModelScope.launch {
            if (!validateTransfer()) return@launch
            _state.value = _state.value.copy(isloading = true, transferError = null)

            delay(2000)
            
            _state.value = _state.value.copy(
                isloading = false,
                transferSuccess = true
            )
        }
    }

    private fun transferSuccess() {
        _state.value = _state.value.copy(transferSuccess = true, isloading = false)
    }
}
