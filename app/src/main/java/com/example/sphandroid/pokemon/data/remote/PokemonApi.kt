package com.example.sphandroid.pokemon.data.remote

import com.example.sphandroid.pokemon.data.remote.dto.PokemonDetailsDto
import com.example.sphandroid.pokemon.data.remote.dto.PokemonResultDto
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApi {

    @GET("pokemon/?limit=500")
    suspend fun getPokemons(): PokemonResultDto

    @GET()
    suspend fun getPokemon(@Url url: String): PokemonDetailsDto
}