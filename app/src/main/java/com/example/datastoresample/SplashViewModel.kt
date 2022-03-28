package com.example.datastoresample

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastoresample.data.DataRepository
import com.example.datastoresample.screens.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    repository: DataRepository
) : ViewModel()  {

    private val _isLoading : MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination : MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination : State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readWelcomePrefs().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.Home.route
                } else {
                    _startDestination.value = Screen.Welcome.route
                }
            }
            _isLoading.value = false
        }
    }

}