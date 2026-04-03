package com.kang.myapplication.ui.player

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kang.myapplication.data.model.Video
import com.kang.myapplication.ui.home.VideoCard
import com.kang.myapplication.ui.theme.LangTubeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    videoId: String,
    onBackClick: () -> Unit,
    onVideoClick: (String) -> Unit,
    viewModel: PlayerViewModel = viewModel(factory = PlayerViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(videoId) {
        viewModel.fetchVideoDetails(videoId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Watch", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            when (val state = uiState) {
                is PlayerUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is PlayerUiState.Success -> {
                    VideoDetails(
                        video = state.video,
                        relatedVideos = state.relatedVideos,
                        onVideoClick = onVideoClick
                    )
                }
                is PlayerUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun VideoDetails(video: Video, relatedVideos: List<Video> = emptyList(), onVideoClick: (String) -> Unit = {}) {
    var isPlaying by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Video Player Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            if (isPlaying) {
                val videoId = video.videoUrl
                    .substringAfter("?v=")
                    .substringBefore("&")
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            settings.javaScriptEnabled = true
                            settings.domStorageEnabled = true
                            settings.mediaPlaybackRequiresUserGesture = false
                            settings.allowContentAccess = true
                            settings.loadWithOverviewMode = true
                            settings.useWideViewPort = true
                            webChromeClient = WebChromeClient()
                            webViewClient = WebViewClient()
                            loadUrl("https://www.youtube.com/embed/$videoId?autoplay=1&playsinline=1")
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                AsyncImage(
                    model = video.thumbnailUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.6f
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { isPlaying = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
        
        // Video Info
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${video.viewCount} • ${video.publishedAt} • #${video.language}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ActionButton(icon = Icons.Outlined.ThumbUp, label = "12K")
                ActionButton(icon = Icons.Outlined.ThumbDown, label = "")
                ActionButton(icon = Icons.Outlined.Share, label = "Share")
                ActionButton(icon = Icons.Outlined.Download, label = "Download")
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            
            // Channel Info
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = video.author.take(1),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = video.author,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "1.5M subscribers",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Button(
                    onClick = { /* Subscribe */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Subscribe")
                }
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            
            // Description Preview
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = video.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Related Videos
            if (relatedVideos.isNotEmpty()) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = "Related Videos",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                relatedVideos.forEach { related ->
                    VideoCard(video = related, onClick = { onVideoClick(related.id) })
                }
            }
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, modifier = Modifier.size(18.dp))
            if (label.isNotEmpty()) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = label, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlayerScreenPreview() {
    LangTubeTheme {
        val mockVideo = Video(
            id = "1",
            title = "Learning Kotlin in 10 Minutes - The ultimate guide to modern Android development",
            description = "A quick guide to Kotlin programming language. Kotlin is a modern, cross-platform, statically typed, general-purpose programming language with type inference. This tutorial covers everything you need to start building apps.",
            thumbnailUrl = "https://img.youtube.com/vi/F9UC9DY-vIU/maxresdefault.jpg",
            videoUrl = "https://www.youtube.com/watch?v=F9UC9DY-vIU",
            author = "Code Academy",
            viewCount = "1.2M views",
            publishedAt = "2 days ago",
            language = "English"
        )
        Scaffold { padding ->
            Box(modifier = Modifier.padding(padding)) {
                VideoDetails(video = mockVideo)
            }
        }
    }
}
