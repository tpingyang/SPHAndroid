package com.example.sphandroid.domain.repository

import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails

interface pokemonRepository {

    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemon(url: String): PokemonDetails
}