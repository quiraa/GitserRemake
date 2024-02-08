package com.quiraadev.jetusergithub.ui.screens.setting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiraadev.jetusergithub.core.data.preference.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(): ViewModel() {

    private val _isDarkMode = mutableStateOf(AppPreference.darkMode)
    val isDarkMode : State<Boolean> = _isDarkMode

    fun setDarkMode(value : Boolean) {
        viewModelScope.launch {
            AppPreference.darkMode = value
            _isDarkMode.value = value
        }
    }
}