package com.example.ma7faza.presentation.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.presentation.components.Buttons
import com.example.ma7faza.presentation.components.MyTopBar
import com.example.ma7faza.presentation.components.TextFields
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.SoraFontFamily
import com.example.ma7faza.R
import com.example.ma7faza.presentation.components.LoadingIndicator


@Composable
fun RegisterScreen(
    viewModel: registerViewModel = viewModel(),
    onBackClick: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state){
        when{
        state.backclick -> onBackClick()
        state.createsuccess -> onRegisterSuccess()
        }
    }

    RegisterScreenContent(
        state = state,
        onIntent = viewModel::processIntent
    )
}

@Composable
fun RegisterScreenContent(
    state: RegisterState,
    onIntent:(RegisterIntent) -> Unit) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopBar(
                showBackButton = true,
                onBackClick = { onIntent(RegisterIntent.BackClicked) }
            )
        }
    ) { paddingValues ->
        Box {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Text(
                    text = stringResource(R.string.sign_up_title),
                    color = BlackPurple,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    fontFamily = SoraFontFamily
                )

                Spacer(modifier = Modifier.height(60.dp))

                TextFields(
                    value = state.name,
                    onValueChange = { onIntent(RegisterIntent.NameChanged(it)) },
                    label = stringResource(R.string.name),
                    keyboardType = KeyboardType.Text,
                    onTogglePasswordVisibility = {},
                    errorMessage = state.nameError)

                Spacer(modifier = Modifier.height(16.dp))

                TextFields(
                    value = state.mobileNumber,
                    onValueChange = { onIntent(RegisterIntent.MobileNumberChanged(it)) },
                    label = stringResource(R.string.Mobile_Number),
                    keyboardType = KeyboardType.Phone,
                    onTogglePasswordVisibility = {},
                    errorMessage = state.mobileError)

                Spacer(modifier = Modifier.height(16.dp))

                TextFields(
                    value = state.password,
                    onValueChange = { onIntent(RegisterIntent.PasswordChanged(it)) },
                    label = stringResource(R.string.Password),
                    isPasswordField = true,
                    keyboardType = KeyboardType.Password,
                    passwordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = {
                        onIntent(RegisterIntent.TogglePasswordVisibility)
                    },
                    errorMessage = state.passwordError)

                Spacer(
                    modifier = Modifier.height(
                        30
                            .dp
                    )
                )

                Buttons(
                    text = stringResource(R.string.sign_up_button),
                    onClick = { onIntent(RegisterIntent.CreateAccountClicked) })


            }
            LoadingIndicator(
                isLoading = state.isLoading,
                modifier = Modifier,
                screenWidth = screenWidth,
                screenHeight = screenHeight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent(
        state = RegisterState(),
        onIntent = {}
    )
}
