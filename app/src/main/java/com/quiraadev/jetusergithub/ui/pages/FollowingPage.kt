package com.quiraadev.jetusergithub.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailViewModel
import com.quiraadev.jetusergithub.ui.widget.ErrorContent
import com.quiraadev.jetusergithub.ui.widget.LoadingContent
import com.quiraadev.jetusergithub.ui.widget.UserCardItem

@Composable
fun FollowingPage(
    navController: NavHostController,
    detailViewModel : DetailViewModel
) {
    return detailViewModel.detailUserFollowState.collectAsState(ResultState.Loading).value.let { state ->
        when(state) {
            is ResultState.Error -> ErrorContent(message = state.errorMessage)
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                val followings = state.data
                LazyColumn {
                    items(items = followings, key = { it.login }) {user ->
                        UserCardItem(user = user, onClickUser = { username ->
                            detailViewModel.getUserDetail(username)
                            navController.navigate(Screen.Detail.createRoute(username))
                        })
                    }
                }
            }
        }
    }
}