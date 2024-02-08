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
import androidx.compose.material.icons.filled.Clear
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
import coil.compose.AsyncImage
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite

@Composable
fun AvailableFavoriteContent(
    favorites: List<Favorite>,
    onClickUser: (username: String) -> Unit,
    onDeleteClick: (Favorite) -> Unit,
    onClearFavorite: () -> Unit
) {
    return LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Delete All Favorite", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    onClearFavorite()
                }) {
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear Icon", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
        items(items = favorites, key = { it.id }) { favorite ->
            FavoriteCard(
                favoriteUser = favorite,
                onClickUser = onClickUser,
                onDeleteClick = onDeleteClick
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
    onClickUser: (username: String) -> Unit,
    onDeleteClick: (Favorite) -> Unit
) {
    val (id, photoUrl, login) = favoriteUser

    return ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {
            onClickUser(favoriteUser.login)
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
                onDeleteClick(favoriteUser)
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