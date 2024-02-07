package com.quiraadev.jetusergithub.ui.screens.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
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
                val favorites = state.data
                FavoriteContent(
                    listFavorites = favorites,
                    navController = navController,
                    onUpdateFavoriteUser = favoriteViewModel::updateFavoriteUser
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavorites: List<Favorite>,
    navController: NavHostController,
    onUpdateFavoriteUser: (id: Int, isFavorite: Boolean) -> Unit
) {
    return when (listFavorites.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableFavoriteContent(
            favorites = listFavorites,
            navController = navController,
            onUpdateFavoriteUser = onUpdateFavoriteUser
        )
    }
}