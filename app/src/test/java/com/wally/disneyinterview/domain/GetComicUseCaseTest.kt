package com.wally.disneyinterview.domain

import com.wally.disneyinterview.data.ComicData
import com.wally.disneyinterview.data.MarvelComicsRepository
import com.wally.disneyinterview.data.TextObject
import com.wally.disneyinterview.data.Thumbnail
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class GetComicUseCaseTest {

    @Test
    fun getComic_case_returnsPopulatedComic() {
        runBlocking {
            val repository = Mockito.mock(MarvelComicsRepository::class.java)
            Mockito.`when`(repository.getComic(COMIC_ID)).thenReturn(getTestComicData())
            val getComicUseCase = GetComicUseCase(repository)
            val comic = getComicUseCase.invoke(COMIC_ID)
            assert(TITLE == comic.title)
            assert(DESCRIPTION == comic.description)
            assert(IMAGE_URL == comic.imageUrl)
        }
    }

    private fun getTestComicData(): ComicData {
        val textObject = TextObject(DESCRIPTION)
        val thumbnail = Thumbnail(PATH, EXTENSION)
        return ComicData(TITLE, listOf(textObject), thumbnail)
    }

    companion object {
        private const val TITLE = "title"
        private const val PATH = "path"
        private const val EXTENSION = "extension"
        private const val DESCRIPTION = "description"
        private const val COMIC_ID = "1"
        private const val IMAGE_URL = "$PATH.$EXTENSION"
    }
}