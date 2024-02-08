@file:OptIn(
    ExperimentalFoundationApi::class
)

package com.quiraadev.jetusergithub.ui.screens.detail

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import com.quiraadev.jetusergithub.core.data.remote.response.DetailUserResponse
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.pages.FollowerPage
import com.quiraadev.jetusergithub.ui.pages.FollowingPage
import com.quiraadev.jetusergithub.ui.screens.favorite.FavoriteViewModel
import com.quiraadev.jetusergithub.ui.widget.ErrorContent
import com.quiraadev.jetusergithub.ui.widget.LoadingContent
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    username: String,
    navController: NavHostController,
    detailViewModel: DetailViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    LaunchedEffect(key1 = true) {
        detailViewModel.getUserDetail(username)
    }

    detailViewModel.detailUserState.collectAsState(ResultState.Loading).value.let { state ->
        when (state) {
            is ResultState.Error -> ErrorContent(
                message = state.errorMessage,
                callbackRefresh = {
                    detailViewModel.getUserDetail(username)
                }
            )
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                val user = state.data
                DetailContent(
                    user = user,
                    navController = navController,
                    detailViewModel = detailViewModel,
                    favoriteViewModel = favoriteViewModel
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    user: DetailUserResponse,
    navController: NavHostController,
    detailViewModel: DetailViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val pagerState = rememberPagerState(pageCount = { 2 }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    var isFavorite by remember { mutableStateOf(false) }

    val context = LocalContext.current

    favoriteViewModel.allFavoriteUser.collectAsState(ResultState.Loading).value.let { state ->
        when (state) {
            is ResultState.Error -> ErrorContent(message = state.errorMessage)
            is ResultState.Loading -> LoadingContent()
            is ResultState.Success -> {
                val favorites = state.data
                isFavorite = favorites.any { it.id == user.id }
            }
        }
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        detailViewModel.onFollowsEvent(user.login)
    }

    val tabItems = listOf(
        TabItem(
            "Follower"
        ) { FollowerPage(navController = navController, detailViewModel = detailViewModel) },
        TabItem(
            "Following"
        ) { FollowingPage(navController = navController, detailViewModel = detailViewModel) }
    )

    when (pagerState.currentPage) {
        0 -> detailViewModel.setFollowsEvent(DetailEvent.OnFollower)
        1 -> detailViewModel.setFollowsEvent(DetailEvent.OnFollowing)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surfaceVariant),
                onClick = {
                    Screen.pop(navController)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = { isChecked ->
                    isFavorite = isChecked
                    if (isChecked) {
                        favoriteViewModel.insertFavorite(
                            Favorite(
                                id = user.id,
                                avatarUrl = user.avatarUrl,
                                login = user.login
                            )
                        )
                        Toast.makeText(context, "User Added", Toast.LENGTH_SHORT).show()
                    } else {
                        favoriteViewModel.deleteFavorite(
                            Favorite(
                                id = user.id,
                                avatarUrl = user.avatarUrl,
                                login = user.login
                            )
                        )
                        Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                val icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                Icon(
                    imageVector = icon,
                    contentDescription = "Favorite Icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = user.login,
            modifier = Modifier
                .size(108.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = user.login,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = user.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Follower : ${user.followers}",
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Following : ${user.following}",
                modifier = Modifier.weight(0.5f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.surface,
            indicator = { position ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(
                        position[pagerState.currentPage]
                    )
                )
            }
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            tabItems[page].screen()
        }
    }
}

data class TabItem(
    val title: String,
    val screen: @Composable () -> Unit
)


