package com.quiraadev.jetusergithub.core.di

import android.content.Context
import androidx.room.Room
import com.quiraadev.jetusergithub.core.data.local.FavoriteDao
import com.quiraadev.jetusergithub.core.data.local.FavoriteDatabase
import com.quiraadev.jetusergithub.core.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        val authInterceptor = Interceptor { chain ->
            val requestHeader = chain.request().newBuilder()
                .addHeader("Authorization", "token {Use with your own github personal access token}")
                .build()
            chain.proceed(requestHeader)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FavoriteDatabase =
        Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite.db"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()


    @Provides
    fun provideDao(database: FavoriteDatabase): FavoriteDao = database.dao
}
