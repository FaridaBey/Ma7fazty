package com.example.ma7faza.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ma7faza.domain.data.TransactionItem
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun TransactionRow(
    name: String,
    date: String,
    amount: String,
    icon: Int, // Resource ID for the logo
    IsCredit: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Box
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F7F9)), // Light grey background
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified // Keeps original logo colors
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = SoraFontFamily,
                    color = Color.Black
                )
                Text(
                    text = date,
                    fontSize = 12.sp,
                    fontFamily = SoraFontFamily,
                    color = Color.Gray
                )
            }

            // Amount
            Text(
                text = if (IsCredit) "+$$amount" else "-$$amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                color = if (IsCredit) Color(0xFF4CAF50) else Color(0xFFE57373)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
        // Thin Divider
        HorizontalDivider(
            modifier = Modifier.padding(start = 64.dp),
            thickness = 1.dp,
            color = Color.LightGray.copy(alpha = 0.2f)
        )
    }
}
@Composable
fun TransactionList(
    transactions: List<TransactionItem>,
    modifier: Modifier = Modifier,
    onTransactionClick: (TransactionItem) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = transactions,
            key = { it.id }
        ) { transaction ->
            TransactionRow(
                name = transaction.name,
                date = transaction.date,
                amount = transaction.amount,
                icon = transaction.icon,
                IsCredit = transaction.isCredit,
                onClick = { onTransactionClick(transaction) }
            )
        }
    }
}
