package com.example.ma7faza.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ma7faza.domain.data.TransactionFilter
import com.example.ma7faza.presentation.history.HistoryIntent
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.Purple1
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun TransactionFilterRow(
    selectedFilter: TransactionFilter,
    onFilterSelected: (TransactionFilter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TransactionFilter.entries.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                colors = FilterChipDefaults.filterChipColors(
                    // Colors when the chip IS selected
                    selectedContainerColor = Purple1,
                    selectedLabelColor = BlackPurple,
                    selectedLeadingIconColor = Purple1,

                    // Colors when the chip is NOT selected
                    containerColor = Color.Transparent,
                    labelColor = BlackPurple
                ),
                label = {
                    Text(
                        text = when (filter) {
                            TransactionFilter.ALL -> "All"
                            TransactionFilter.INCOME -> "Income"
                            TransactionFilter.EXPENSE -> "Expenses"
                        },
                        fontFamily = SoraFontFamily
                    )
                }
            )
        }
    }
}


