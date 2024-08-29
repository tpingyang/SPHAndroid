package com.example.sphandroid.pokemon.domain.repository

import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.model.PokemonDetails

interface PokemonRepository {

    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemon(url: String): PokemonDetails
}