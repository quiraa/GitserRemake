package com.quiraadev.jetusergithub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chibatching.kotpref.Kotpref
import com.quiraadev.jetusergithub.ui.screens.setting.SettingViewModel
import com.quiraadev.jetusergithub.ui.theme.JetUserGithubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Kotpref.init(this)
        setContent {
            val settingViewModel = hiltViewModel<SettingViewModel>()
            JetUserGithubTheme(
                darkTheme = settingViewModel.isDarkMode.value
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        homeViewModel = hiltViewModel(),
                        detailViewModel = hiltViewModel(),
                        favoriteViewModel = hiltViewModel(),
                        settingViewModel = hiltViewModel()
                    )
                }
            }
        }
    }
}