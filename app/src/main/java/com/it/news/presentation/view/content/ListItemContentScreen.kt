package com.it.news.presentation.view.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.it.news.R
import com.it.news.domain.model.Article
import com.it.news.domain.util.toLocalDate

@Composable
fun ListItemContentScreen(article: Article, onClick: () -> Unit) {
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Spacer(modifier = Modifier.padding(bottom = 15.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 15.dp)
                    .height(30.dp)
                    .width(30.dp)
                    .clickable(onClick = onClick)
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = article.title,
                style = TextStyle(letterSpacing = TextUnit(1f, TextUnitType.Sp)),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = article.description!!,
                style = TextStyle(letterSpacing = TextUnit(1f, TextUnitType.Sp)),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.padding(bottom = 25.dp))
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = "by ${article.author}",
                style = TextStyle(letterSpacing = TextUnit(1f, TextUnitType.Sp)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(bottom = 5.dp))
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = article.publishedAt.toLocalDate(),
                style = TextStyle(letterSpacing = TextUnit(1f, TextUnitType.Sp)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.padding(bottom = 25.dp))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.urlToImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.padding(bottom = 25.dp))
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                text = article.content!!,
                style = TextStyle(letterSpacing = TextUnit(1.5f, TextUnitType.Sp)),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}