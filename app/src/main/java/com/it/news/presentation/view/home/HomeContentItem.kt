package com.it.news.presentation.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.it.news.domain.model.Article
import com.it.news.domain.util.toLocalDate
import com.it.news.ui.theme.liteGray

@Composable
fun HomeContentItem(
    index: Int, size: Int,
    article: Article, onItemClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.clickable(onClick = onItemClick)
    ) {
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(bottom = 15.dp, end = 18.dp)
        ) {
            Text(
                text = article.source.name,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 3,
                fontSize = 13.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Normal,
                color = liteGray
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = article.publishedAt.toLocalDate(),
                style = MaterialTheme.typography.subtitle2,
                maxLines = 3,
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
        HomeContentImageview(
            article.urlToImage!!,
            modifier = Modifier
                .weight(1.3f)
                .height(90.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
    if (index != size - 1) {
        HorizontalDivider(
            color = Color.LightGray,
            thickness = .8.dp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}