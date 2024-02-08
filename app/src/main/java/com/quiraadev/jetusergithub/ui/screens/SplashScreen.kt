package com.quiraadev.jetusergithub.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.R
import com.quiraadev.jetusergithub.ui.navigations.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController : NavHostController
) {
    LaunchedEffect(key1 = true) {
        delay(1800)
        Screen.pushAndReplace(navController, Screen.Home.route)
    }

    return Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.gitser),
                contentDescription = "App Icon",
                modifier = Modifier.size(86.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "GitserRemake", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
