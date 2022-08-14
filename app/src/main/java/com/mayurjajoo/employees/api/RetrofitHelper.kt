package com.mayurjajoo.employees.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Holds instance of Retrofit
 * Helper class to fetch data from Network calls.
 */
object RetrofitHelper {
    private const val BASE_URL = "http://demo2143341.mockable.io/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}