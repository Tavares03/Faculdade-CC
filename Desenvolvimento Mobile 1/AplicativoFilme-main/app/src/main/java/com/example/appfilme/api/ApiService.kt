package com.example.appfilme.api

import com.example.appfilme.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    fun getFilmesEmAlta(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"
    ): Call<MovieResponse>
}