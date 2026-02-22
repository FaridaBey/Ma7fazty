package com.example.ma7faza.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.domain.data.WindowType
import com.example.ma7faza.presentation.components.BottomBar
import com.example.ma7faza.presentation.components.BottomDialog
import com.example.ma7faza.presentation.components.ProfileInfoField
import com.example.ma7faza.presentation.history.HistoryIntent
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.Purple0
import com.example.ma7faza.ui.theme.Purple1
import com.example.ma7faza.ui.theme.SoraFontFamily

@Composable
fun ProfileScreen(
    viewModel: profileViewModel = viewModel(),
    onHistoryClick: () -> Unit,
    onHomeClick: () -> Unit,
    onTransferClick: () -> Unit

) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        when {
            state.historyClicked -> onHistoryClick()
            state.homeClicked -> onHomeClick()
            state.transferClicked -> onTransferClick()
        }
    }


    ProfileScreenContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}
@Composable
fun ProfileScreenContent(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit
)  {
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = 3,
                onHomeClick = {},
                onHistoryClick = {},
                onTransferClick = { },
                onProfileClick = { }
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
                text = "Profile",
                color = BlackPurple,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = SoraFontFamily,
                fontSize = 24.sp,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Purple0.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.name.firstOrNull()?.uppercase() ?: "",
                    color = Purple0,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 100.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            ProfileInfoField(
                icon = Icons.Default.Person,
                iconContainerColor = Purple0,
                iconTint = Color.White,
                label = "Name",
                value = state.name,
                onEditClick = {onIntent(ProfileIntent.editName) }
            )
            ProfileInfoField(
                icon = Icons.Default.Phone,
                iconContainerColor = Purple0,
                iconTint = Color.White,
                label = "Mobile",
                value = state.phoneNumber,
                onEditClick = {},
                isedit = false
            )
            ProfileInfoField(
                icon = Icons.Default.Email,
                iconContainerColor = Purple0,
                iconTint = Color.White,
                label = "Email",
                value = state.email,
                onEditClick = {onIntent(ProfileIntent.editEmail)}
            )
            ProfileInfoField(
                icon = Icons.Default.Lock,
                iconContainerColor = Purple0,
                iconTint = Color.White,
                onEditClick = {onIntent(ProfileIntent.ChangePassword)},
                ispassword = true,
                isedit = false,
                label = " ",
                value = " "
            )

        }
    }
    // --- THE BOTTOM DIALOG ---
//    BottomDialog(
//        visible = state.isDialogVisible,
//        onDismiss = {onIntent(ProfileIntent.DismissDialog)},
//        Type = WindowType.EDIT_PROFILE,
//        editLabel = state.editLabel,
//        editValue = state.editValue,
//        onValueChange = {onIntent(ProfileIntent.OnValueChange(it))},
//        onSave = {onIntent(ProfileIntent.Save)}
//    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent(state = ProfileState(), onIntent = {})
}
