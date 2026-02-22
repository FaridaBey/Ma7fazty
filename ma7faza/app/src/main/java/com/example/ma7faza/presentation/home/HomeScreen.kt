package com.example.ma7faza.presentation.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.presentation.components.BottomBar
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.SoraFontFamily
import com.example.ma7faza.R
import com.example.ma7faza.presentation.components.BalanceCard
import com.example.ma7faza.presentation.components.TransactionList
import com.example.ma7faza.ui.theme.PurpleGrey40

import kotlin.Unit

@Composable
fun HomeScreen(
    viewModel: homeViewModel = viewModel(),
    onTransferClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onProfileClick: () -> Unit

) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        when {
            state.transferClicked -> onTransferClick()
            state.historyClicked -> onHistoryClick()
            state.profileClicked -> onProfileClick()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.processIntent(HomeIntent.LoadHome)
    }

    HomeScreenContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}

@Composable
fun HomeScreenContent(state: HomeState, onIntent: (HomeIntent) -> Unit) {
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = 0,
                onHomeClick = {},
                onHistoryClick = {onIntent(HomeIntent.HistoryClicked)},
                onTransferClick = {onIntent(HomeIntent.TransferClicked)},
                onProfileClick = {onIntent(HomeIntent.ProfileClicked)}
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            BalanceCard(
                onProfileClick = { onIntent(HomeIntent.ProfileClicked) },
                onToggleBalanceVisibility = { onIntent(HomeIntent.ToggleBalanceVisibility) },
                BalanceVisible = state.isbalanceVisible,
                name = state.name,
                balance = state.balance
            )


            Spacer(Modifier.height(24.dp))

            // ------- Transaction --------
            Row() {
                Text(
                    text = stringResource(R.string.Latest_trans),
                    modifier = Modifier.padding(horizontal = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = SoraFontFamily,
                    color = BlackPurple
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.view_all),
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable { onIntent(HomeIntent.HistoryClicked) },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = SoraFontFamily,
                    color = PurpleGrey40

                )
            }
            Spacer(Modifier.height(12.dp))
            TransactionList(
                transactions = state.transactions,
                onTransactionClick = { onIntent(HomeIntent.HistoryClicked)}
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(state = HomeState(), onIntent = {})
}
