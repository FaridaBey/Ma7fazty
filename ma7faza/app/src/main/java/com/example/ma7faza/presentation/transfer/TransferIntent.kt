package com.example.ma7faza.presentation.transfer

sealed class TransferIntent {
    object OnHomeClick : TransferIntent()
    object OnProfileClick : TransferIntent()
    object OnHistoryClick : TransferIntent()
    data class OnAmountChange(val amount: String) : TransferIntent()
    object OnTransferClick : TransferIntent()
    object OnTransferSuccess : TransferIntent()
    object OnTransferError : TransferIntent()
    data class OnRecipientNumberChange(val number: String) : TransferIntent()

}

