package com.example.sphandroid.data.remote

import com.example.sphandroid.data.remote.dto.PokemonDetailsDto
import com.example.sphandroid.data.remote.dto.PokemonResultDto
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon/?limit=100")
    suspend fun getPokemons(): PokemonResultDto

    @GET()
    suspend fun getPokemon(@Url url: String): PokemonDetailsDto
}