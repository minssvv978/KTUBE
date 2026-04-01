package com.kang.myapplication.data.remote

import com.kang.myapplication.data.model.Video
import com.kang.myapplication.data.model.VideoList
import kotlinx.coroutines.delay

class MockVideoApiService : VideoApiService {
    private val allVideos = listOf(
        Video(
            id = "1",
            title = "Learning Kotlin in 10 Minutes",
            description = "A quick guide to Kotlin programming language. Kotlin is a modern, cross-platform, statically typed, general-purpose programming language with type inference.",
            thumbnailUrl = "https://img.youtube.com/vi/F9UC9DY-vIU/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=F9UC9DY-vIU",
            author = "Code Academy",
            viewCount = "1.2M views",
            publishedAt = "2 days ago",
            language = "English"
        ),
        Video(
            id = "2",
            title = "Apprendre le Français avec des films",
            description = "Learn French with movies and series. This video covers common phrases and vocabulary used in French cinema.",
            thumbnailUrl = "https://img.youtube.com/vi/3qV36fT_L6I/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=3qV36fT_L6I",
            author = "French Hub",
            viewCount = "500K views",
            publishedAt = "1 week ago",
            language = "French"
        ),
        Video(
            id = "3",
            title = "Advanced Compose UI Patterns",
            description = "Master Jetpack Compose with advanced patterns. Learn about state management, side effects, and custom layouts.",
            thumbnailUrl = "https://img.youtube.com/vi/0z_B3I9C1_A/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=0z_B3I9C1_A",
            author = "Android Devs",
            viewCount = "800K views",
            publishedAt = "3 days ago",
            language = "English"
        ),
        Video(
            id = "4",
            title = "Curso de Android con Kotlin",
            description = "Complete Android course in Spanish. Desde cero hasta profesional, aprende a crear aplicaciones modernas.",
            thumbnailUrl = "https://img.youtube.com/vi/chv7-f0U4_U/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=chv7-f0U4_U",
            author = "Programación TV",
            viewCount = "2M views",
            publishedAt = "1 month ago",
            language = "Spanish"
        ),
        Video(
            id = "5",
            title = "Top 10 Places to Visit in Japan",
            description = "Discover the beauty of Japan. From Tokyo to Kyoto, explore the best tourist destinations.",
            thumbnailUrl = "https://img.youtube.com/vi/G3Xm8mS9hUo/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=G3Xm8mS9hUo",
            author = "Traveler Guru",
            viewCount = "3.5M views",
            publishedAt = "5 days ago",
            language = "English"
        ),
        Video(
            id = "6",
            title = "Introducción a Jetpack Compose",
            description = "Introduction to Jetpack Compose in Spanish. Aprende los conceptos básicos de la interfaz de usuario declarativa.",
            thumbnailUrl = "https://img.youtube.com/vi/1_8W_T_9hL8/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=1_8W_T_9hL8",
            author = "Android Latino",
            viewCount = "150K views",
            publishedAt = "2 weeks ago",
            language = "Spanish"
        )
    )

    override suspend fun getVideos(language: String?, query: String?): VideoList {
        delay(1000) // Simulate network delay
        val filtered = allVideos.filter { video ->
            (language == null || language.isEmpty() || video.language.equals(language, ignoreCase = true)) &&
            (query == null || video.title.contains(query, ignoreCase = true))
        }
        return VideoList(filtered)
    }

    override suspend fun getVideo(id: String): Video {
        delay(500)
        return allVideos.find { it.id == id } ?: throw Exception("Video not found")
    }
}
