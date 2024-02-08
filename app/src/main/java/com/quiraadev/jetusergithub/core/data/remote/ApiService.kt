package com.quiraadev.jetusergithub.core.data.remote

import com.quiraadev.jetusergithub.core.data.remote.response.DetailUserResponse
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.core.data.remote.response.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun fetchListUser(): ListUserResponse

    @GET("users/{username}")
    suspend fun fetchUserDetail(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/followers")
    suspend fun fetchUserFollowers(
        @Path("username") username: String
    ): ListUserResponse

    @GET("users/{username}/following")
    suspend fun fetchUserFollowing(
        @Path("username") username: String
    ): ListUserResponse

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") username: String
    ) : SearchUserResponse

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}