package com.example.ma7faza.presentation.components


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon

import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.Purple0
import com.example.ma7faza.ui.theme.Purple1
import com.example.ma7faza.ui.theme.SoraFontFamily
import java.util.concurrent.BlockingQueue


@Composable
fun ProfileInfoField(
    icon: ImageVector,
    iconContainerColor: Color,
    iconTint: Color,
    label: String,
    value: String,
    onEditClick: () -> Unit,
    ispassword: Boolean = false,
    isedit: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Icon Container
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = iconContainerColor, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            if (ispassword) {
                Text(
                    text = "Change password",
                    color = BlackPurple,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = SoraFontFamily,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                // 2. Text Labels
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontFamily = SoraFontFamily
                    )
                    Text(
                        text = value,
                        color = BlackPurple,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = SoraFontFamily
                    )
                }
                if (isedit) {

                    // 3. Edit Button
                    Text(
                        text = "Edit",
                        color = Purple0,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { onEditClick() }
                    )
                }
            }
        }


        HorizontalDivider(
            thickness = 1.dp,
            color = Purple1
        )
    }
}