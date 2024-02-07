package com.quiraadev.jetusergithub.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.ui.widget.UserCardItem

//@Composable
//fun FollowingPage(
//    followings : ListUserResponse,
//    onClickUser : (username: String) -> Unit,
//) {
//    return LazyColumn {
//        items(items = followings, key = { it.login }) {user ->
//            UserCardItem(user = user, onClickUser = onClickUser)
//        }
//    }
//}