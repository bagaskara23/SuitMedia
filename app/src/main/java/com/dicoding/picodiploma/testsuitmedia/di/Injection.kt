package com.dicoding.picodiploma.testsuitmedia.di

import android.content.Context
import com.dicoding.picodiploma.testsuitmedia.UserDatabase
import com.dicoding.picodiploma.testsuitmedia.UserRepository
import com.dicoding.picodiploma.testsuitmedia.api.Retrofit

object Injection {

    fun provideRepository(context: Context): UserRepository {

        val database = UserDatabase.getDatabase(context)
        val apiService = Retrofit.apiService

        return UserRepository(database, apiService)
    }
}