package com.gobinda.compose.multiplatform.sample.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gobinda.compose.multiplatform.sample.common.collectAsStateMultiplatform
import com.gobinda.compose.multiplatform.sample.component.ActionButton
import com.gobinda.compose.multiplatform.sample.component.ClickableLabelText
import com.gobinda.compose.multiplatform.sample.component.InputField
import com.gobinda.compose.multiplatform.sample.component.Loading
import com.gobinda.compose.multiplatform.sample.component.PasswordField
import com.gobinda.compose.multiplatform.sample.component.Spacer10
import com.gobinda.compose.multiplatform.sample.utils.TripleState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SignUpScreen(vm: SignUpViewModel = koinViewModel(), onNavigateSignIn: () -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val uiState by vm.uiState.collectAsStateMultiplatform()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState)}) {
        LaunchedEffect(uiState){
            uiState.message?.let {
                snackbarHostState.showSnackbar(it)
            }
        }
        uiState.apply {
            if (success) {
                onNavigateSignIn()
            } else {
                SignUpContent(
                    name,
                    email,
                    pass,
                    cPass,
                    loading,
                    onValueChange = { _name, _email, _pass, _cPass ->
                        _name?.let {
                            vm.onEvent(SignUpEvent.Name(it))
                        }
                        _email?.let {
                            vm.onEvent(SignUpEvent.Email(it))
                        }
                        _pass?.let {
                            vm.onEvent(SignUpEvent.Pass(it))
                        }
                        _cPass?.let {
                            vm.onEvent(SignUpEvent.Confirm(it))
                        }
                    },
                    onValidate = {
                        focusManager.clearFocus()
                        vm.onEvent(SignUpEvent.Validate)
                    }, onNavigateSignIn = onNavigateSignIn
                )
            }
        }
    }

}

@Composable
fun SignUpContent(
    name: TripleState<String>,
    email: TripleState<String>,
    pass: TripleState<String>,
    cPass: TripleState<String>,
    loading: Boolean = false,
    onValueChange: (
        name: String?,
        email: String?,
        pass: String?,
        cPass: String?
    ) -> Unit,
    onValidate: () -> Unit,
    onNavigateSignIn: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Sign Up", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))
        Spacer(modifier = Modifier.heightIn(30.dp))
        with(name) {
            InputField(
                label = "Name",
                value = value,
                isError = isError,
                errorMessage = message
            ) {
                onValueChange(it, null, null, null)
            }
        }
        Spacer10()
        with(email) {
            InputField(
                label = "Email",
                value = value, keyboardType = KeyboardType.Email,
                isError = isError,
                errorMessage =  message
            ) {
                onValueChange(null, it, null, null)
            }
        }
        Spacer10()
        PasswordField(
            label = "Password",
            value = pass.value,
            isError = pass.isError,
            errorMessage =  pass.message,
            imeAction = ImeAction.Next
        ) {
            onValueChange(null, null, it, null)
        }
        Spacer10()
        PasswordField(
            label = "Confirm Password",
            value = cPass.value,
            isError = cPass.isError,
            errorMessage = cPass.message,
            onDone = {
                onValidate()
            }) {
            onValueChange(null, null, null, it)
        }

        Spacer(modifier = Modifier.heightIn(30.dp))

        Box(modifier = Modifier.padding(60.dp, 0.dp, 60.dp, 0.dp)) {

            ActionButton(label = "Submit") {
                onValidate()
            }
        }
        Spacer(modifier = Modifier.heightIn(30.dp))
        ClickableLabelText(
            modifier = Modifier,
            label = "SignIn here",
            textDecoration = TextDecoration.Underline
        ) {
            onNavigateSignIn()
        }
    }

    if (loading) {
        Loading()
    }
}

@Preview
@Composable
fun SignViewPreview() {
    SignUpScreen {
    }
}