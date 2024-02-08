package com.quiraadev.jetusergithub.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.widget.AvailableFavoriteContent
import com.quiraadev.jetusergithub.ui.widget.EmptyContent
import com.quiraadev.jetusergithub.ui.widget.ErrorContent
import com.quiraadev.jetusergithub.ui.widget.LoadingContent

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    return favoriteViewModel.allFavoriteUser.collectAsState(ResultState.Loading).value.let { state ->
        when (state) {
            is ResultState.Error -> ErrorContent(message = state.errorMessage)
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                FavoriteContent(
                    listFavorites = state.data,
                    onClickUser = { username ->
                        Screen.push(navController, Screen.Detail.createRoute(username))
                    },
                    onDeleteClick = { favorite ->
                        favoriteViewModel.deleteFavorite(favorite)
                    },
                    onClearFavorite = {
                        favoriteViewModel.deleteAllFavorite()
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavorites: List<Favorite>,
    onClickUser: (username: String) -> Unit,
    onDeleteClick: (Favorite) -> Unit,
    onClearFavorite: () -> Unit,
) {
    return when (listFavorites.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableFavoriteContent(
            favorites = listFavorites,
            onClickUser = onClickUser,
            onDeleteClick = onDeleteClick,
            onClearFavorite = onClearFavorite
        )
    }
}