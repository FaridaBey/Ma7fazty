package com.example.ma7faza.presentation.history


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.R
import com.example.ma7faza.domain.data.WindowType
import com.example.ma7faza.presentation.components.BottomBar
import com.example.ma7faza.presentation.components.BottomDialog
import com.example.ma7faza.presentation.components.CustomSearchBar
import com.example.ma7faza.presentation.components.TransactionFilterRow
import com.example.ma7faza.presentation.components.TransactionList
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.SoraFontFamily


@Composable
fun HistoryScreen(
    viewModel: historyViewModel = viewModel(),
    onTransferClick: () -> Unit,
    onHomeClick: () -> Unit,
    onProfileClick: () -> Unit

) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        when {
            state.transferClicked -> onTransferClick()
            state.homeClicked -> onHomeClick()
            state.profileClicked -> onProfileClick()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.processIntent(HistoryIntent.LoadHistory)
    }

    HistoryScreenContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreenContent(
    state: HistoryState,
    onIntent: (HistoryIntent) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = 1,
                onHomeClick = { onIntent(HistoryIntent.OnHomeClick) },
                onHistoryClick = {},
                onTransferClick = { onIntent(HistoryIntent.OnTransferClick) },
                onProfileClick = { onIntent(HistoryIntent.OnProfileClick) }
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
                text = stringResource(R.string.History),
                color = BlackPurple,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                fontSize = 24.sp,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(15.dp))

            // --- SEARCH BAR SECTION ---
            CustomSearchBar(
                query = state.query,
                onQueryChange = { onIntent(HistoryIntent.OnSearchQueryChange(it)) },
                onClearClick = { onIntent(HistoryIntent.OnClearSearch) }
            )
            // --- filter ----

            TransactionFilterRow(
                selectedFilter = state.selectedFilter,
                onFilterSelected = { filter -> onIntent(HistoryIntent.OnFilterChange(filter)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // -- transactions --
            TransactionList(
                transactions = state.filteredTransactions,
                onTransactionClick = {onIntent(HistoryIntent.OnTransactionClick(it))}
            )

        }
        // --- THE BOTTOM DIALOG ---
        BottomDialog(
            visible = state.isDialogVisible,
            onDismiss = {onIntent(HistoryIntent.DismissDialog)},
            Type = WindowType.TRANSACTION,
            transaction = state.selectedTransaction
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreenContent(state = HistoryState(), onIntent = {})
}
