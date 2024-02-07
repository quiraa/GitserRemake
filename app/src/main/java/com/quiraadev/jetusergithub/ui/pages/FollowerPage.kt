package com.quiraadev.jetusergithub.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.ui.widget.UserCardItem

//@Composable
//fun FollowerPage(
//    followers : ListUserResponse,
//    onClickUser : (username: String) -> Unit
//) {
//    return LazyColumn {
//        items(items = followers, key = { it.login }) { user ->
//            UserCardItem(user = user,  onClickUser = onClickUser)
//        }
//    }
//}