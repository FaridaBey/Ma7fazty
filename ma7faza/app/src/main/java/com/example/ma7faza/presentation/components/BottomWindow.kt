package com.example.ma7faza.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ma7faza.domain.data.TransactionItem
import com.example.ma7faza.domain.data.WindowType
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun BottomDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    onSave: () -> Unit = {},
    Type: WindowType,
    transaction: TransactionItem? = null,
    editLabel: String = "",
    editValue: String = "",
    onValueChange: (String) -> Unit = {},
    isSuccess: Boolean = true,
    contentAlignment: Alignment = Alignment.BottomCenter
) {
    if (!visible) return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.4f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onDismiss() },
        contentAlignment = contentAlignment
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(Color.White)
                .clickable(enabled = false) {} // Prevent click-through to background
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray
                    )
                }
            }

            when (Type) {
                WindowType.TRANSACTION -> {
                    transaction?.let {
                        TransactionDetails(it)
                    }
                }
                WindowType.EDIT_PROFILE -> {
                    EditProfileContent(
                        label = editLabel,
                        value = editValue,
                        onValueChange = onValueChange,
                        onSave = onSave
                    )
                }
                WindowType.TRANSFER_RESULT -> {
                    TransferResultContent(
                        isSuccess = isSuccess,
                        onDone = onDismiss
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TransactionDetails(transaction: TransactionItem) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(0.35f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF5F7F9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = transaction.icon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = transaction.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                color = BlackPurple
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                .padding(10.dp)
        ) {
            Text(
                text = transaction.date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF444C5E)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background((if (transaction.isCredit) Color(0xFFF1F8E9) else Color(0xFFFFF5F5)))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (transaction.isCredit) "+$${transaction.amount}" else "-$${transaction.amount}",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isCredit) Color(0xFF4CAF50) else Color(0xFFA63232)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Transaction ID",
                fontFamily = SoraFontFamily,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = "#${transaction.id}",
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                color = Color.Black,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun EditProfileContent(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Edit $label",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SoraFontFamily,
            color = BlackPurple
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label, fontFamily = SoraFontFamily) },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BlackPurple,
                focusedLabelColor = BlackPurple
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BlackPurple)
        ) {
            Text(
                text = "Save",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun TransferResultContent(
    isSuccess: Boolean,
    onDone: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (isSuccess) Icons.Default.CheckCircle else Icons.Default.Error,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = if (isSuccess) Color(0xFF4CAF50) else Color(0xFFE57373)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isSuccess) "Transfer Successful" else "Transfer Failed",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SoraFontFamily,
            color = BlackPurple
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isSuccess) "Your money has been sent successfully." else "Something went wrong. Please try again.",
            fontSize = 14.sp,
            fontFamily = SoraFontFamily,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onDone,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BlackPurple)
        ) {
            Text(
                text = "Done",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                fontSize = 16.sp
            )
        }
    }
}
