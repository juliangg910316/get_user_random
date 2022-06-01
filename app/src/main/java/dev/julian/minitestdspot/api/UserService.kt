package dev.julian.minitestdspot.api

import dev.julian.minitestdspot.data.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api")
    suspend fun loadUser(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String,
        @Query("inc") inc: String,      // picture, name, email, location
    ) : UserResponse

    companion object {
        private const val BASE_URL = "https://randomuser.me/"

        fun create(): UserService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService::class.java)
        }
    }
}