@file:OptIn(ExperimentalMaterial3Api::class)

package com.quiraadev.jetusergithub.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.core.data.remote.response.UserResponseItem
import com.quiraadev.jetusergithub.ui.navigations.Screen
import com.quiraadev.jetusergithub.ui.screens.detail.DetailViewModel


@Composable
fun AvailableUserContent(
    users: ListUserResponse,
    onClickUser: (username: String) -> Unit
) {
    return LazyColumn {
        items(items = users, key = { it.id }) { user ->
            UserCardItem(user = user, onClickUser = onClickUser)
        }
    }
}

@Composable
fun UserCardItem(
    user: UserResponseItem,
    onClickUser : (username: String) -> Unit
) {
    val (photoUrl, _, _, _, _, _, _, id, login, _, _, _, _, _, _, _, _, _) = user

    return OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {
            onClickUser(user.login)
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
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = login,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "ID: $id",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}
