package com.dicoding.picodiploma.testsuitmedia.api

import com.dicoding.picodiploma.testsuitmedia.dataclass.GetUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    @GET("api/users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): GetUserResponse
}
