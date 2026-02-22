package com.example.ma7faza.presentation.transfer

data class TransferState (
    val homeClicked: Boolean = false,
    val profileClicked: Boolean = false,
    val historyClicked: Boolean = false,
    val isloading: Boolean = false,
    val amount: String = "",
    val amountError: String? = null,
    val transferSuccess: Boolean = false,
    val transferError: String? = null,
    val recipientNumber: String = " ",
    val recipientNumberError: String? = null
)