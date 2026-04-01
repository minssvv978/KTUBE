package com.kang.myapplication.data

import android.content.Context
import com.kang.myapplication.data.remote.MockVideoApiService
import com.kang.myapplication.data.remote.VideoApiService
import com.kang.myapplication.data.repository.LanguageRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val videoApiService: VideoApiService
    val languageRepository: LanguageRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    // Using Mock API for now as the base URL is a placeholder
    override val videoApiService: VideoApiService by lazy {
        MockVideoApiService()
    }

    override val languageRepository: LanguageRepository by lazy {
        LanguageRepository(context)
    }
}
