package com.example.ma7faza.domain.data

data class TransactionItem (
    val id: Int,
    val name: String,
    val date: String,
    val amount: String,
    val icon: Int,
    val isCredit: Boolean = false,
    val Status: String = "Pending"
)