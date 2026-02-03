package com.example.ma7faza.presentation.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ma7faza.R
import com.example.ma7faza.presentation.components.Buttons
import com.example.ma7faza.presentation.components.MyTopBar
import com.example.ma7faza.presentation.components.TextFields
import com.example.ma7faza.ui.theme.BlackPurple
import com.example.ma7faza.ui.theme.Purple0
import com.example.ma7faza.ui.theme.SoraFontFamily
import kotlinx.coroutines.delay
import com.example.ma7faza.presentation.components.LoadingIndicator


@Composable
fun LoginScreen(
    viewModel: loginViewModel = viewModel(),
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        when{
            state.loginsuccess -> onLoginSuccess()
            state.signupSuccess -> onSignUpClick()
        }
    }

    LoginScreenContent(
        state  =  state,
        onIntent = viewModel::processIntent
    )
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopBar(
                showBackButton = false
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
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = stringResource(R.string.login_title),
                    color = BlackPurple,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    fontFamily = SoraFontFamily,
                    fontSize = 24.sp,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = stringResource(R.string.login_icon),
                    modifier = Modifier.size(screenWidth * 0.5f, screenHeight * 0.2f)
                )


                Spacer(modifier = Modifier.height(30.dp))

                TextFields(
                    value = state.mobileNumber,
                    onValueChange = { onIntent(LoginIntent.MobileNumberChanged(it)) },
                    label = stringResource(R.string.Mobile_Number),
                    keyboardType = KeyboardType.Phone,
                    onTogglePasswordVisibility = {},
                    errorMessage = state.mobileError)



                Spacer(modifier = Modifier.height(16.dp))

                TextFields(
                    value = state.password,
                    onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
                    label = stringResource(R.string.Password),
                    isPasswordField = true,
                    keyboardType = KeyboardType.Password,
                    passwordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = { onIntent(LoginIntent.TogglePasswordVisibility) },
                    errorMessage = state.passwordError)



                Spacer(
                    modifier = Modifier.height(
                        60
                            .dp
                    )
                )

                Buttons(
                    text = stringResource(R.string.Login),
                    onClick = { onIntent(LoginIntent.LoginClicked) })

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { onIntent(LoginIntent.SignUpClicked) }) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        color = Purple0,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = SoraFontFamily
                    )
                }

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
fun LoginScreenPreview() {
    LoginScreenContent(
        state = LoginState(),
        onIntent = {}
    )
}
