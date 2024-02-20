package com.wally.disneyinterview.ui

import com.wally.disneyinterview.domain.Comic

sealed interface ComicUiState {
    data object Loading: ComicUiState
    data class Success(val comic: Comic): ComicUiState
    data class Error(val message: String): ComicUiState
}