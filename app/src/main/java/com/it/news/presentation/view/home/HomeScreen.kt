package com.it.news.presentation.view.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.it.news.navigation.Screen
import com.it.news.ui.theme.blue

@Composable
fun HomeScreen(
    navController: NavController,
    contentPadding: PaddingValues,
    viewmodel: HomeViewmodel
) {
    val uiEvent by viewmodel.headlines.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(start = 18.dp, top = 20.dp, end = 18.dp)
    ) {
        when (uiEvent) {
            is HomeUiEvent.Failure -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                    ) {
                        Icon(
                            Icons.Rounded.Info,
                            contentDescription = "Image",
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = uiEvent.message!!, textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            is HomeUiEvent.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .drawBehind {
                                drawCircle(
                                    blue,
                                    radius = size.width / 2 - 5.dp.toPx() / 2,
                                    style = Stroke(5.dp.toPx())
                                )
                            },
                        color = Color.LightGray,
                        strokeWidth = 5.dp
                    )
                }
            }

            is HomeUiEvent.Success -> {
                Text(
                    text = "NEWS HEADLINES",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    color = blue
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    items(uiEvent.articles!!.size) { index ->
                        HomeContentItem(
                            index,
                            uiEvent.articles!!.size,
                            uiEvent.articles!![index],
                            onItemClick = {
                                val contentJsonString = Gson().toJson(uiEvent.articles!![index])
                                navController.navigate(Screen.Content.route + "?content=${contentJsonString}")
                            }
                        )
                    }
                }
            }
        }
    }
}