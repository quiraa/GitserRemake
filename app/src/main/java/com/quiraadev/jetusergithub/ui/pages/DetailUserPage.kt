package com.quiraadev.jetusergithub.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.remote.response.DetailUserResponse
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailViewModel
import com.quiraadev.jetusergithub.ui.widget.ErrorContent
import com.quiraadev.jetusergithub.ui.widget.LoadingContent
import com.quiraadev.jetusergithub.ui.widget.UserCardItem

@Composable
fun DetailUserPage(
    user: DetailUserResponse
) {
    return Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Hello my name is ${user.name}, I Live in ${user.location}, and I work in ${user.company}")
    }
}

@Composable
fun FollowerPage(
    navController: NavHostController,
    detailViewModel : DetailViewModel
) {
    return detailViewModel.detailUserFollowState.collectAsState(ResultState.Loading).value.let { state ->
        when(state) {
            is ResultState.Error -> ErrorContent(message = state.errorMessage)
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                val follower = state.data
                LazyColumn {
                    items(items = follower, key = { it.login }) {user ->
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