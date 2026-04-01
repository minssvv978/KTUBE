package com.kang.myapplication.data.remote

import com.kang.myapplication.data.model.Video
import com.kang.myapplication.data.model.VideoList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApiService {
    @GET("videos")
    suspend fun getVideos(
        @Query("language") language: String? = null,
        @Query("search") query: String? = null
    ): VideoList

    @GET("videos/{id}")
    suspend fun getVideo(@Path("id") id: String): Video

    companion object {
        const val BASE_URL = "https://api.langtube.mock/" // Placeholder URL
    }
}
