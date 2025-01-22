package com.jay.seekhotask.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import com.jay.seekhotask.model.datamodel.anime_list.Data
import com.jay.seekhotask.viewmodel.AnimeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
 fun LandingScreen(navController: NavHostController) {
    val viewModel: AnimeViewModel = koinViewModel()
    val animeData by viewModel.animeList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAnimeList()
    }
    if (errorMessage != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage!!, color = Color.Black)
        }
    } else if(animeData.isNotEmpty()) {
        LazyColumn {
            items(items = animeData) { data ->
                MyListItem(data) {
                    navController.navigate(AnimeDetail(data.mal_id.toString()))
                }
            }
        }
    }else{
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

            CircularProgressIndicator(modifier = Modifier.size(36.dp))
        }
    }


}
@Composable
 fun MyListItem(item: Data, onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val imageWidth = screenWidth * 0.45f
    val imageHeight = screenHeight * 0.30f


    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            SubcomposeAsyncImage(
                loading = {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                },
                modifier = Modifier.size(width = imageWidth, height = imageHeight).clip(
                    RoundedCornerShape(16.dp)
                ),
                model = item.images.jpg.image_url,
                contentScale = ContentScale.Fit,
                contentDescription = item.title,

                )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Box(modifier = Modifier.fillMaxSize()){
                    Column {

                    }
                }
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Total Episodes: ${item.episodes}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    text = item.rating,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Row {
                    Icon(Icons.Rounded.Star, tint = Color.Yellow, contentDescription = item.title)
                    Text(
                        text = item.score.toString(),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                // Button at the bottom
                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    onClick()
                }) {
                    Text("Details")
                }
            }

        }

    }
}
