package com.quiraadev.jetusergithub.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailViewModel
import com.quiraadev.jetusergithub.ui.widget.AvailableUserContent
import com.quiraadev.jetusergithub.ui.widget.EmptyContent
import com.quiraadev.jetusergithub.ui.widget.ErrorContent
import com.quiraadev.jetusergithub.ui.widget.LoadingContent
import com.quiraadev.jetusergithub.ui.widget.SearchField

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val homeState by homeViewModel.homeState.collectAsState()

    LaunchedEffect(key1 = true) {
        homeViewModel.getListUser()
    }

    return homeViewModel.allUserState.collectAsState(ResultState.Loading).value.let { state ->
        when (state) {
            is ResultState.Error -> ErrorContent(message = state.errorMessage)
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                HomeContent(
                    users = state.data,
                    onClickUser = { username ->
                        detailViewModel.getUserDetail(username)
                        navController.navigate(Screen.Detail.createRoute(username))
                    },
                    query = homeState.query,
                    onQueryChange = {
                        homeViewModel.setQuery(it)
                    },
                    onSearch = {
                        homeViewModel.searchUser(homeState.query)
                    },
                    onClearQuery = {
                        homeViewModel.setQuery("")
                        homeViewModel.getListUser()
                    }
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    users: ListUserResponse,
    onClickUser: (username: String) -> Unit,
    query: String,
    onQueryChange: (newQuery: String) -> Unit,
    onSearch: () -> Unit,
    onClearQuery: () -> Unit,
) {
    return Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            value = query,
            onValueChange = onQueryChange,
            onSearch = onSearch,
            onClearQuery = onClearQuery
        )
        when (users.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableUserContent(users = users, onClickUser = onClickUser)
        }
    }

}