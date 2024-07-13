package com.gobinda.compose.multiplatform.sample.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.compose.multiplatform.sample.domain.model.User
import com.gobinda.compose.multiplatform.sample.domain.usecase.RegisterUserUseCase
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event.SignUpEvent
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state.SignUpState
import com.gobinda.compose.multiplatform.sample.utils.isValidEmail
import com.gobinda.compose.multiplatform.sample.utils.isValidPassword

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sample.composeapp.generated.resources.Res
import sample.composeapp.generated.resources.confirm_password_empty
import sample.composeapp.generated.resources.email_empty
import sample.composeapp.generated.resources.email_incorrect
import sample.composeapp.generated.resources.name_empty
import sample.composeapp.generated.resources.password_empty
import sample.composeapp.generated.resources.password_not_matched
import sample.composeapp.generated.resources.password_should

class SignUpViewModel constructor(
    private val registerUser: RegisterUserUseCase, /*savedStateHandle: SavedStateHandle*/
) : ViewModel() {

    private var _uiState = MutableStateFlow(SignUpState())

    val uiState: StateFlow<SignUpState>
        get() = _uiState

    fun onEvent(event: SignUpEvent){
        when(event){
            is SignUpEvent.Confirm -> setConfirmPass(event.name)
            is SignUpEvent.Email -> setEmail(event.name)
            is SignUpEvent.Name -> setName(event.name)
            is SignUpEvent.Pass -> setPass(event.name)
            is SignUpEvent.Validate -> validate()
        }
    }


   private fun validate() {
        viewModelScope.launch {
            var newState = _uiState.value
            with(_uiState.value) {
                if (name.value.isBlank()) {
                    newState = copy(name = name.copy(isError = true, message = Res.string.name_empty))
                }

                if (email.value.isBlank()) {
                    newState =
                        newState.copy(email = email.copy(isError = true, message = Res.string.email_empty))

                } else  if (!email.value.isValidEmail) {
                    newState =
                        newState.copy(email = email.copy(isError = true, message = Res.string.email_incorrect))

                }
                if (pass.value.isBlank()) {
                    newState =
                        newState.copy(pass = pass.copy(isError = true, message = Res.string.password_empty))
                } else if (!pass.value.isValidPassword) {
                    newState =
                        newState.copy(pass = pass.copy(isError = true, message = Res.string.password_should))
                }

                if (cPass.value.isBlank()) {
                    newState =
                        newState.copy(cPass = cPass.copy(isError = true, message = Res.string.confirm_password_empty))
                } else if (pass.value != cPass.value) {
                    newState = newState.copy(
                        cPass = cPass.copy(
                            isError = true, message = Res.string.password_not_matched
                        )
                    )
                }
            }

            _uiState.value = newState

            if (!(newState.name.isError || newState.email.isError || newState.pass.isError || newState.cPass.isError)) {
                newState = newState.copy(loading = true)
                _uiState.value = newState

                val handler = CoroutineExceptionHandler { _, exception ->
                    newState = newState.copy(loading = false, success = false, message = "Email all ready exist!")
                    _uiState.value = newState
                }

                viewModelScope.launch(handler) {
                    delay(3000)

                    registerUser(with(_uiState.value){ User(email.value, name = name.value, pass.value) }).also {

                            withContext(Dispatchers.Main) {
                                newState = newState.copy(loading = false, success = true)
                                _uiState.value = newState
                            }
                    }

                }
            }

        }

    }

    private fun setName(_name: String) {
        with(_uiState.value) {
            _uiState.value = copy(name = name.copy(_name, isError = false), message = null)
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

    private fun setConfirmPass(_cPass: String) {
        with(_uiState.value) {
            _uiState.value = copy(cPass = cPass.copy(_cPass, isError = false), message = null)
        }
    }

}