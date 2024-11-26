package com.it.news.presentation.view.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.it.news.domain.model.News

@Composable
fun ScrollableTab(
    navController: NavController,
    categories: List<News>
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        categories.size
    }
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(containerColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            selectedTabIndex = selectedIndex,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedIndex])
                        .fillMaxSize()
                        .padding(10.dp)
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            FilterChipDefaults.shape,
                        )
                )
            }) {
            categories.fastForEachIndexed { tabIndex, item ->
                FilterChip(modifier = Modifier
                    .wrapContentSize()
                    .zIndex(2f),
                    selected = false,
                    border = null,
                    onClick = { selectedIndex = tabIndex },
                    label = {
                        Text(text = item.country, textAlign = TextAlign.Center)
                    })
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            userScrollEnabled = true
        ) {
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { currentPage ->
                    selectedIndex = currentPage
                    pagerState.animateScrollToPage(currentPage)
                }
            }
            NewsContentItem(navController, categories[selectedIndex].articles)
        }
    }
}