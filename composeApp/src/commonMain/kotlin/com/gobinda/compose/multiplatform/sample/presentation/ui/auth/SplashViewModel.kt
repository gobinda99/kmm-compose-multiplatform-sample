package com.gobinda.compose.multiplatform.sample.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gobinda.compose.multiplatform.sample.data.d_local.datasource.AppDataStore
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.event.SplashEvent
import com.gobinda.compose.multiplatform.sample.presentation.ui.auth.state.SplashState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class SplashViewModel(
    private val pref: AppDataStore,
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
                    }
                }.stateIn(viewModelScope)
        }
    }

    private suspend fun login() {
        viewModelScope.launch {
            _state.value = SplashState.Loading
            delay(1000L)
            _state.value = SplashState.Success(anyUserLoggedIn())
        }
    }


    private suspend fun anyUserLoggedIn(): Boolean = pref.getBoolean("logIn") ?: false

    private suspend fun logout() = pref.store("logIn", false)
}