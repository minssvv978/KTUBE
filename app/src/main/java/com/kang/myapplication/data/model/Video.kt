package com.kang.myapplication.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model representing a video in the LangTube app.
 */
@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String,
    @Json(name = "videoUrl") val videoUrl: String,
    @Json(name = "author") val author: String,
    @Json(name = "viewCount") val viewCount: String,
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "language") val language: String,
    @Json(name = "isKids") val isKids: Boolean = false
)

/**
 * Data model for a list of videos returned by the API.
 */
@JsonClass(generateAdapter = true)
data class VideoList(
    @Json(name = "videos") val videos: List<Video>
)
