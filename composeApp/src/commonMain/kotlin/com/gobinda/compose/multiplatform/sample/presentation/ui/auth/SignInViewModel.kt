package com.gobinda.compose.multiplatform.sample.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.compose.multiplatform.sample.domain.usecase.LoginUserUseCase
import com.gobinda.compose.multiplatform.sample.domain.usecase.UpdateLoggedUserUseCase
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event.SignInEvent
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state.SignInState
import com.gobinda.compose.multiplatform.sample.utils.isValidEmail
import com.gobinda.compose.multiplatform.sample.utils.isValidPassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sample.composeapp.generated.resources.Res
import sample.composeapp.generated.resources.email_empty
import sample.composeapp.generated.resources.email_incorrect
import sample.composeapp.generated.resources.not_record_found
import sample.composeapp.generated.resources.password_empty
import sample.composeapp.generated.resources.password_should

class SignInViewModel(
    private val login: LoginUserUseCase,
    private val updateLogin: UpdateLoggedUserUseCase
) : ViewModel() {


    private var _uiState = MutableStateFlow(SignInState())

    val uiState: StateFlow<SignInState>
        get() = _uiState

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.Email -> setEmail(event.name)
            is SignInEvent.Pass -> setPass(event.name)
            is SignInEvent.Validate -> validate()
        }
    }

    private fun validate() {
        viewModelScope.launch {
            var newState = uiState.value
            with(uiState.value) {
                if (email.value.isBlank()) {
                    newState =
                        copy(email = email.copy(isError = true, message = Res.string.email_empty))
                } else if (!email.value.isValidEmail) {
                    newState =
                        copy(
                            email = email.copy(
                                isError = true,
                                message = Res.string.email_incorrect
                            )
                        )
                }
                if (pass.value.isBlank()) {
                    newState =
                        newState.copy(
                            pass = pass.copy(
                                isError = true,
                                message = Res.string.password_empty
                            )
                        )
                } else if (!pass.value.isValidPassword) {
                    newState =
                        newState.copy(
                            pass = pass.copy(
                                isError = true,
                                message = Res.string.password_should
                            )
                        )
                }
            }
            _uiState.value = newState
            if (!(newState.email.isError || newState.pass.isError)) {
                newState = newState.copy(loading = true)
                _uiState.value = newState
                /*viewModelScope.launch {
                    delay(5000)
                    newState = newState.copy(loading = false, success = true)
                    _uiState.value = newState

                }*/

                login(_uiState.value.email.value, _uiState.value.pass.value)
                    .map {
                        if (it.getOrNull() != null) {
                            newState = newState.copy(loading = false, success = true)
                            _uiState.value = newState
                            updateLogin(true)
                        } else {
                            newState = newState.copy(
                                loading = false,
                                success = false,
                                message = Res.string.not_record_found
                            )
                            _uiState.value = newState
                        }

                    }
                    .stateIn(
                        scope = viewModelScope,
                    )
            }
        }

    }

    private fun setEmail(_email: String) {
        with(_uiState.value) {
            _uiState.value = copy(email = email.copy(_email, isError = false), message = null)
        }
    }

    private fun setPass(_pass: String) {
        with(_uiState.value) {
            _uiState.value = copy(pass = pass.copy(_pass, isError = false), message = null)
        }
    }

}