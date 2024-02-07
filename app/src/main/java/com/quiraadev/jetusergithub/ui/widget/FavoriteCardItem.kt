package com.quiraadev.jetusergithub.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import com.quiraadev.jetusergithub.ui.navigations.Screen

@Composable
fun AvailableFavoriteContent(
    favorites: List<Favorite>,
    navController: NavHostController,
    onUpdateFavoriteUser: (id: Int, isFavorite: Boolean) -> Unit
) {
    return LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        items(items = favorites, key = { it.id }) { favorite ->
            FavoriteCard(
                favoriteUser = favorite,
                navController = navController,
                onUpdateFavoriteUser = onUpdateFavoriteUser
            )
        }
        item {
            Text(
                text = "That's all for now",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun FavoriteCard(
    favoriteUser: Favorite,
    navController: NavHostController,
    onUpdateFavoriteUser: (id: Int, isFavorite: Boolean) -> Unit
) {
    val (id, photoUrl, login) = favoriteUser

    return ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {
            navController.navigate(Screen.Detail.createRoute(favoriteUser.login))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = login,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = login,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Favorite ID: $id",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            IconButton(onClick = {
                onUpdateFavoriteUser(favoriteUser.id, favoriteUser.isFavorite)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}