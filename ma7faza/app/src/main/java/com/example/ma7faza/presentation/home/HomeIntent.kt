package com.example.ma7faza.presentation.home


sealed class HomeIntent{
    object TransferClicked : HomeIntent()
    object HistoryClicked : HomeIntent()
    object ProfileClicked : HomeIntent()
    object ToggleBalanceVisibility : HomeIntent ()
    object LoadHome : HomeIntent()
}


