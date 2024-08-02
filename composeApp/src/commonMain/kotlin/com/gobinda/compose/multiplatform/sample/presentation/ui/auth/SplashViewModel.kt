package com.gobinda.compose.multiplatform.sample.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.compose.multiplatform.sample.domain.usecase.GetLoggedUserUseCase
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event.SplashEvent
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state.SplashState
import io.ktor.client.HttpClient
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SplashViewModel(
    private val userLogged: GetLoggedUserUseCase,
) : ViewModel() {

    val userIntent = Channel<SplashEvent>(Channel.UNLIMITED)

    private var _state = MutableStateFlow<SplashState>(SplashState.Loading)

    val state: StateFlow<SplashState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow()
                .onEach {
                    when (it) {
                        SplashEvent.LogIn -> {
                            login()
                        }
                        else -> {}
                    }
                }.stateIn(viewModelScope)
        }
    }

    private suspend fun login() {
        viewModelScope.launch {
            _state.value = SplashState.Loading
            delay(1000L)
            _state.value = SplashState.Success(userLogged())
        }
    }


}