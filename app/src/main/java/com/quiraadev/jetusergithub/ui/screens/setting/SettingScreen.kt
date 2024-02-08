package com.quiraadev.jetusergithub.ui.screens.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel
) {
    val darkMode = settingViewModel.isDarkMode.value

    return Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dark Mode", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = darkMode,
                onCheckedChange = { isDarkMode ->
                    settingViewModel.setDarkMode(isDarkMode)
                },
                thumbContent = {
                    val icon = if (darkMode) Icons.Filled.DarkMode else Icons.Filled.LightMode
                    Icon(imageVector = icon, contentDescription = "Thumb Icon", modifier = Modifier.padding(4.dp))
                },
            )
        }
    }
}