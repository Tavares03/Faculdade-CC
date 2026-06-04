package com.example.appfilme.api

import com.example.appfilme.model.CreditosResponse
import com.example.appfilme.model.GeneroResponse
import com.example.appfilme.model.MovieResponse
import com.example.appfilme.model.ReleaseDatesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    fun getFilmesEmAlta(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("search/movie")
    fun buscarFilmes(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("genre/movie/list")
    fun getGeneros(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Call<GeneroResponse>

    @GET("movie/{id}/credits")
    fun getCreditos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Call<CreditosResponse>

    @GET("movie/{id}/release_dates")
    fun getClassificacao(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<ReleaseDatesResponse>
}