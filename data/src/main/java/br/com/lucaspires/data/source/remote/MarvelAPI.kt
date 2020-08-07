package br.com.lucaspires.data.source.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("/v1/public/characters")
    fun getAllCharacters(
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int
    ): Single<Nothing>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int
    ): Single<Nothing>

    @GET("/v1/public/characters/{characterId}/series")
    fun getSeries(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int
    ): Single<Nothing>
}