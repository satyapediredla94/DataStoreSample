package com.example.datastoresample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datastoresample.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    fun saveWelcomeScreenState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveWelcomePrefs(completed = completed)
        }
    }

}