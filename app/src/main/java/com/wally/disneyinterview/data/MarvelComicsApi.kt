package com.wally.disneyinterview.data

import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelComicsApi {

    @GET("/v1/public/comics?limit=100")
    suspend fun getComics()

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComic(@Path("comicId") comicId: String): MarvelComicsResponse<ComicData>
}