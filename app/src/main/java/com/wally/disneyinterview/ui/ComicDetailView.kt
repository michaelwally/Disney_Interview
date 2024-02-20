package com.wally.disneyinterview.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wally.disneyinterview.domain.Comic
import com.wally.disneyinterview.ui.theme.DisneyInterviewTheme

@Composable
fun ComicDetailView(viewModel: MainViewModel) {
    val comicState by viewModel.comicUiState.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    DisneyInterviewTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            when (comicState) {
                is ComicUiState.Success -> {
                    ComicDetail(comic = (comicState as ComicUiState.Success).comic)
                }
                is ComicUiState.Error -> {
                    Error((comicState as ComicUiState.Error).message)
                }
                ComicUiState.Loading -> Loading()
                null -> Error("null comic")
            }
        }
    }
}

@Composable
fun ComicDetail(comic: Comic) {
    Column {
        Row {
            AsyncImage(
                model = comic.imageUrl,
                contentScale = ContentScale.Fit,
                contentDescription = "Comic Image",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = comic.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f).testTag(Tag.TITLE_TEST_TAG),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = comic.description)
    }
}

@Composable
fun Loading() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(message: String) {
    Text(text = message, fontWeight = FontWeight.Bold, fontSize = 18.sp)
}

object Tag {
    const val TITLE_TEST_TAG = "Title"
}