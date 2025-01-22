package com.jay.seekhotask.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.jay.seekhotask.model.datamodel.anime_detail.AnimeData
import com.jay.seekhotask.viewmodel.AnimeDetailsViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.compose.koinViewModel


@Composable
fun AnimeDetailScreen(id: String, navController: NavHostController) {
    val viewModel: AnimeDetailsViewModel = koinViewModel()
    val animeData by viewModel.animeData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    LaunchedEffect(Dispatchers.IO) {
        viewModel.fetchAnimeData(id)
    }

//    val context = LocalContext.current
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            repeatMode = ExoPlayer.REPEAT_MODE_ALL
//            playWhenReady = true
//            prepare()
//        }
//    }
//    DisposableEffect(exoPlayer) {
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//    animeData?.data?.trailer?.url?.let { embedUrl ->
//        LaunchedEffect(embedUrl) {
//            exoPlayer.setMediaItem(MediaItem.fromUri(embedUrl))
//            exoPlayer.prepare()
//        }
//    }
    if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage!!, color = Color.Black)
        }
    } else if(animeData != null) {
        AnimeDetailContent(animeData = animeData,navController)
    }else{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            CircularProgressIndicator(modifier = Modifier.size(36.dp))
        }
    }



}

@Composable
fun AnimeDetailContent(animeData: AnimeData?,navController: NavHostController) {
    val scrollState = rememberScrollState()
    val isButtonVisible = remember { derivedStateOf { scrollState.value == 0 } }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .verticalScroll(scrollState)
        ) {
//            YoutubeScreen(animeData?.data?.trailer?.youtube_id.toString(), androidx.lifecycle.compose.LocalLifecycleOwner.current)
            SubcomposeAsyncImage(
                loading = {
                    CircularProgressIndicator()
                },
                model = animeData?.data?.images?.jpg?.large_image_url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                contentDescription = animeData?.data?.title.toString()
            )
            Text(
                animeData?.data?.title.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = "No of episodes: "+" " +animeData?.data?.episodes.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = "Age rating :"+animeData?.data?.rating.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Row (   modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),){
                Text(
                    text = "Rating: \t",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Icon(Icons.Rounded.Star, tint = Color.Yellow, contentDescription = animeData?.data?.title)
                Text(
                    text = animeData?.data?.score.toString(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            }
            Text(
                "Genre:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animeData?.data?.genres?.joinToString(", ") { it.name } ?: "No genres available",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )

            Text(
                "Plot/Synopsis:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                animeData?.data?.synopsis.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                "Producers: ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = animeData?.data?.producers?.joinToString(", ") { it.name } ?: "No producer available",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

        }
        AnimatedVisibility(
            visible = isButtonVisible.value,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            IconButton(
                modifier = Modifier.padding(16.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                onClick = { navController.popBackStack() }
            ) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
            }
        }
    }

}

@Composable
fun YoutubeScreen(
    videoId: String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(), factory = { context ->
            YouTubePlayerView(context = context).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(
                            youTubePlayer:
                            YouTubePlayer
                        ) {
                            super.onReady(youTubePlayer)
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    }
                )
            }
        })
}
