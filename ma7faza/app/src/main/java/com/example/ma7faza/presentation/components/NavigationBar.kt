package com.example.ma7faza.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ma7faza.R
import com.example.ma7faza.ui.theme.Purple0

@Composable
fun BottomBar(
    selectedIndex: Int,
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onTransferClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomBarItem(
                icon = R.drawable.ic_home,
                label = "Home",
                selected = selectedIndex == 0,
                onClick = onHomeClick,
                contentDescription = stringResource(R.string.Home_icon)
            )

            BottomBarItem(
                icon = R.drawable.ic_history,
                label = "History",
                selected = selectedIndex == 1,
                onClick = onHistoryClick,
                contentDescription = stringResource(R.string.History_icon)
            )

            BottomBarItem(
                icon = R.drawable.ic_transfer,
                label = "Transfer",
                selected = selectedIndex == 2,
                onClick = onTransferClick,
                contentDescription = stringResource(R.string.Transfer_icon)
            )

            BottomBarItem(
                icon = R.drawable.ic_profile,
                label = "Profile",
                selected = selectedIndex == 3,
                onClick = onProfileClick,
                contentDescription = stringResource(R.string.Profile_icon)
            )
        }
    }
}
@Composable
private fun BottomBarItem(
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    contentDescription: String
) {
    val tint = if (selected) Purple0 else Color.Gray

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = tint
        )

        Text(
            text = label,
            color = tint
        )
    }
}
