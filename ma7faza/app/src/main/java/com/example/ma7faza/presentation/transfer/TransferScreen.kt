package com.example.ma7faza.presentation.transfer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.R
import com.example.ma7faza.presentation.components.AmountInput
import com.example.ma7faza.presentation.components.BottomBar
import com.example.ma7faza.presentation.components.Buttons
import com.example.ma7faza.presentation.components.CustomSearchBar
import com.example.ma7faza.presentation.components.TextFields
import com.example.ma7faza.presentation.components.TransactionFilterRow
import com.example.ma7faza.presentation.components.TransactionList
import com.example.ma7faza.presentation.history.HistoryIntent
import com.example.ma7faza.presentation.history.HistoryScreenContent
import com.example.ma7faza.presentation.history.HistoryState
import com.example.ma7faza.presentation.history.historyViewModel
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun TransferScreen(
    viewModel: transferViewModel = viewModel(),
    onHistoryClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit

) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        when {
            state.historyClicked -> onHistoryClick()
            state.homeClicked -> onHomeClick()
            state.profileClicked -> onProfileClick()
        }
    }


    TransferScreenContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}
@Composable
fun TransferScreenContent(
    state: TransferState,
    onIntent: (TransferIntent) -> Unit
)  {
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = 2,
                onHomeClick = {onIntent(TransferIntent.OnHomeClick)},
                onHistoryClick = {onIntent(TransferIntent.OnHistoryClick)},
                onTransferClick = { },
                onProfileClick = {onIntent(TransferIntent.OnProfileClick)}
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Transfer",
                color = BlackPurple,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                fontSize = 24.sp,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(150.dp))
            TextFields(
                value = state.recipientNumber,
                onValueChange = { onIntent(TransferIntent.OnRecipientNumberChange(it)) },
                label = "Recipient Mobile Number",
                modifier = Modifier.fillMaxWidth(),
                errorMessage = state.recipientNumberError,
                keyboardType = KeyboardType.Phone,
                isPasswordField = false,
                onTogglePasswordVisibility = {}
            )
            Spacer(modifier = Modifier.height(16.dp))

            AmountInput(
                amount = state.amount,
                onAmountChange = { onIntent(TransferIntent.OnAmountChange(it)) },
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            )
            {
                Spacer(modifier = Modifier.height(16.dp))
                Buttons(
                    onClick = { onIntent(TransferIntent.OnTransferClick) },
                    text = "Transfer",
                    modifier = Modifier.fillMaxWidth(),
                )
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    TransferScreenContent(state = TransferState(), onIntent = {})
}
