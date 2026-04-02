package com.kang.myapplication.data.remote

import com.kang.myapplication.data.model.Video
import com.kang.myapplication.data.model.VideoList
import kotlinx.coroutines.delay

class MockVideoApiService : VideoApiService {
    private val allVideos = listOf(
        // General (non-kids) videos
        Video(
            id = "1",
            title = "Learning Kotlin in 10 Minutes",
            description = "A quick guide to Kotlin programming language.",
            thumbnailUrl = "https://img.youtube.com/vi/F9UC9DY-vIU/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=F9UC9DY-vIU",
            author = "Code Academy",
            viewCount = "1.2M views",
            publishedAt = "2 days ago",
            language = "English",
            isKids = false
        ),
        Video(
            id = "2",
            title = "Apprendre le Français avec des films",
            description = "Learn French with movies and series.",
            thumbnailUrl = "https://img.youtube.com/vi/3qV36fT_L6I/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=3qV36fT_L6I",
            author = "French Hub",
            viewCount = "500K views",
            publishedAt = "1 week ago",
            language = "French",
            isKids = false
        ),
        // Kids videos - English
        Video(
            id = "k1",
            title = "ABC Song for Kids | Alphabet Song",
            description = "Fun alphabet learning song for children. Sing along and learn the ABCs!",
            thumbnailUrl = "https://img.youtube.com/vi/75p-N9YKqNo/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=75p-N9YKqNo",
            author = "Kids Learning Tube",
            viewCount = "120M views",
            publishedAt = "3 days ago",
            language = "English",
            isKids = true
        ),
        Video(
            id = "k2",
            title = "Colors Song for Kids | Learn Colors",
            description = "Learn colors with this fun and colorful song for toddlers and kids.",
            thumbnailUrl = "https://img.youtube.com/vi/JFfDaqHkFWw/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=JFfDaqHkFWw",
            author = "Super Simple Songs",
            viewCount = "85M views",
            publishedAt = "1 week ago",
            language = "English",
            isKids = true
        ),
        Video(
            id = "k3",
            title = "Baby Shark Dance | Sing and Dance",
            description = "Baby Shark is a fun kids song with a catchy tune and silly dance moves!",
            thumbnailUrl = "https://img.youtube.com/vi/XqZsoesa55w/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=XqZsoesa55w",
            author = "Pinkfong",
            viewCount = "13B views",
            publishedAt = "5 years ago",
            language = "English",
            isKids = true
        ),
        Video(
            id = "k4",
            title = "Numbers Song 1-20 | Count with Me",
            description = "Learn numbers from 1 to 20 with this fun counting song for children.",
            thumbnailUrl = "https://img.youtube.com/vi/DaaU_yHoNQs/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=DaaU_yHoNQs",
            author = "Dave and Ava",
            viewCount = "45M views",
            publishedAt = "2 weeks ago",
            language = "English",
            isKids = true
        ),
        // Kids videos - Korean
        Video(
            id = "k5",
            title = "동요 모음 | 어린이 인기 동요",
            description = "어린이들이 좋아하는 인기 동요 모음입니다. 같이 불러봐요!",
            thumbnailUrl = "https://img.youtube.com/vi/LbZYHxbOnEk/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=LbZYHxbOnEk",
            author = "키즈TV",
            viewCount = "50M views",
            publishedAt = "4 days ago",
            language = "Korean",
            isKids = true
        ),
        Video(
            id = "k6",
            title = "한글 배우기 | 가나다라 노래",
            description = "재미있는 노래로 한글 자음과 모음을 배워봐요.",
            thumbnailUrl = "https://img.youtube.com/vi/2BQWLV-MFHM/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=2BQWLV-MFHM",
            author = "뽀로로 채널",
            viewCount = "30M views",
            publishedAt = "1 week ago",
            language = "Korean",
            isKids = true
        ),
        Video(
            id = "k7",
            title = "숫자 배우기 | 1부터 10까지",
            description = "귀여운 캐릭터와 함께 숫자를 배워봐요!",
            thumbnailUrl = "https://img.youtube.com/vi/RjgWGZFVb0Q/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=RjgWGZFVb0Q",
            author = "핑크퐁",
            viewCount = "20M views",
            publishedAt = "2 days ago",
            language = "Korean",
            isKids = true
        ),
        // Kids videos - Spanish
        Video(
            id = "k8",
            title = "El Abecedario en Español para Niños",
            description = "Aprende el abecedario español cantando con los personajes animados.",
            thumbnailUrl = "https://img.youtube.com/vi/0JTbpKlBwpY/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=0JTbpKlBwpY",
            author = "Lunita Pequeña",
            viewCount = "40M views",
            publishedAt = "3 days ago",
            language = "Spanish",
            isKids = true
        ),
        Video(
            id = "k9",
            title = "Los Números del 1 al 10 | Canción Infantil",
            description = "Canta y aprende los números del 1 al 10 con esta divertida canción.",
            thumbnailUrl = "https://img.youtube.com/vi/OBRG0aJvBnQ/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=OBRG0aJvBnQ",
            author = "Toy Cantando",
            viewCount = "25M views",
            publishedAt = "5 days ago",
            language = "Spanish",
            isKids = true
        ),
        // Kids videos - Japanese
        Video(
            id = "k10",
            title = "アイウエオの歌 | ひらがなを覚えよう",
            description = "楽しい歌でひらがなを覚えましょう！子供向け教育動画です。",
            thumbnailUrl = "https://img.youtube.com/vi/1OFDfSNAGrk/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=1OFDfSNAGrk",
            author = "こどもチャンネル",
            viewCount = "15M views",
            publishedAt = "1 week ago",
            language = "Japanese",
            isKids = true
        ),
        Video(
            id = "k11",
            title = "かずの歌 | 1から10まで数えよう",
            description = "アニメキャラクターと一緒に数字を楽しく学ぼう！",
            thumbnailUrl = "https://img.youtube.com/vi/4zGG1qQBqGY/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=4zGG1qQBqGY",
            author = "アンパンマンキッズ",
            viewCount = "10M views",
            publishedAt = "3 days ago",
            language = "Japanese",
            isKids = true
        ),
        // Kids videos - French
        Video(
            id = "k12",
            title = "L'Alphabet en Français pour les Enfants",
            description = "Apprenez l'alphabet français en chantant avec nos personnages animés!",
            thumbnailUrl = "https://img.youtube.com/vi/BELlZKpi1Zs/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=BELlZKpi1Zs",
            author = "Cocorico Kids",
            viewCount = "18M views",
            publishedAt = "4 days ago",
            language = "French",
            isKids = true
        ),
        // Kids videos - German
        Video(
            id = "k13",
            title = "Das Alphabet auf Deutsch für Kinder",
            description = "Lerne das deutsche Alphabet mit einem lustigen Lied und bunten Animationen!",
            thumbnailUrl = "https://img.youtube.com/vi/yGkxmHwOB8I/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=yGkxmHwOB8I",
            author = "Kinder Spaß",
            viewCount = "12M views",
            publishedAt = "6 days ago",
            language = "German",
            isKids = true
        )
    )

    private fun parseViewCount(viewCount: String): Long {
        val cleaned = viewCount.replace(" views", "").trim()
        return when {
            cleaned.endsWith("M") -> (cleaned.dropLast(1).toDouble() * 1_000_000).toLong()
            cleaned.endsWith("K") -> (cleaned.dropLast(1).toDouble() * 1_000).toLong()
            else -> cleaned.toLongOrNull() ?: 0L
        }
    }

    override suspend fun getVideos(language: String?, query: String?): VideoList {
        delay(1000) // Simulate network delay
        val filtered = allVideos
            .filter { video ->
                video.isKids &&
                (language == null || language.isEmpty() || video.language.equals(language, ignoreCase = true)) &&
                (query == null || video.title.contains(query, ignoreCase = true) || video.description.contains(query, ignoreCase = true))
            }
            .sortedByDescending { parseViewCount(it.viewCount) }
        return VideoList(filtered)
    }

    override suspend fun getVideo(id: String): Video {
        delay(500)
        return allVideos.find { it.id == id } ?: throw Exception("Video not found")
    }

    override suspend fun getRelatedVideos(id: String, language: String?): VideoList {
        delay(500)
        val current = allVideos.find { it.id == id }
        val effectiveLanguage = language ?: current?.language
        val related = allVideos
            .filter { video ->
                video.id != id &&
                video.isKids &&
                (effectiveLanguage == null || video.language.equals(effectiveLanguage, ignoreCase = true))
            }
            .sortedByDescending { parseViewCount(it.viewCount) }
            .take(5)
        return VideoList(related)
    }
}
