package com.wally.disneyinterview.domain

import com.wally.disneyinterview.data.MarvelComicsRepository
import javax.inject.Inject

class GetComicUseCase @Inject constructor(
    private val repository: MarvelComicsRepository
) {
    suspend operator fun invoke(comicId: String): Comic {
        val comicData = repository.getComic(comicId)
        val description = comicData.textObjects.first().text
        val imageUrl = "${comicData.thumbnail.path.replace("http", "https")}.${comicData.thumbnail.extension}"
        return Comic(comicData.title, description, imageUrl)
    }
}