package br.com.lucaspires.data.source.remote

import br.com.lucaspires.data.model.remote.CharactersResponse
import br.com.lucaspires.data.model.remote.ContentResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("characters")
    fun getAllCharacters(
        @Query("orderBy") orderBy: String,
        @Query("offset") offset: Int,
        @Query("name") name:String?
    ): Single<CharactersResponse>

    @GET("characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String
    ): Single<ContentResponse>

    @GET("characters/{characterId}/series")
    fun getSeries(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String
    ): Single<ContentResponse>
}