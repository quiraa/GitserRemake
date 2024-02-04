package com.quiraadev.jetusergithub.core.repository

import com.quiraadev.jetusergithub.core.data.local.FavoriteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dao: FavoriteDao
) {

}