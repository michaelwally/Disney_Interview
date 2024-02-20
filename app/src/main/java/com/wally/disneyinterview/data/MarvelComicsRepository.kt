package com.wally.disneyinterview.data

import javax.inject.Inject

class MarvelComicsRepository @Inject constructor(
    private val marvelComicsApi: MarvelComicsApi
) {
    suspend fun getComics() = marvelComicsApi.getComics()

    suspend fun getComic(comicId: String): ComicData =
        marvelComicsApi.getComic(comicId).data.results.first()
}