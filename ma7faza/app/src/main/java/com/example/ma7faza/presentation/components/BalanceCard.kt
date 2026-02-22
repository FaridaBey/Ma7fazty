package com.example.ma7faza.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.Purple0
import com.example.ma7faza.ui.theme.Purple1
import com.example.ma7faza.ui.theme.PurpleVertical
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun BalanceCard(
onProfileClick: () -> Unit,
onToggleBalanceVisibility: () -> Unit,
BalanceVisible: Boolean,
name: String,
balance: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .background(PurpleVertical)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column {
            // ----------- Balance Card   -----------
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ----------- Name -----------
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onProfileClick() }
                ) {

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color.White.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = name.firstOrNull()?.uppercase() ?: "",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }


                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Hello,",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 14.sp,
                            fontFamily = SoraFontFamily
                        )
                        Text(
                            text = name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SoraFontFamily
                        )
                    }
                }

            }

            Spacer(Modifier.height(32.dp))

            // ----------- BALANCE -----------
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = balance,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .blur(
                                radius = if (BalanceVisible) 0.dp else 20.dp
                            ),
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = SoraFontFamily
                    )
                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = "EGP",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 6.dp),
                        fontFamily = SoraFontFamily,

                        )
                }

                Spacer(Modifier.height(18.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onToggleBalanceVisibility() }) {


                    Icon(
                        imageVector = if (BalanceVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(Modifier.width(6.dp))

                    Text(
                        text = if (BalanceVisible)
                            "Hide Balance"
                        else
                            "Show Balance",
                        color = Color.White,
                        fontFamily = SoraFontFamily
                    )
                }
            }
        }
    }
}