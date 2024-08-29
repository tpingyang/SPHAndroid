package com.example.sphandroid.data.repository

import com.example.sphandroid.data.remote.PokemonApi
import com.example.sphandroid.data.remote.dto.toPokemon
import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.domain.repository.pokemonRepository

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi
): pokemonRepository{
    override suspend fun getPokemons(): List<Pokemon> {
        val response = pokemonApi.getPokemons()
        return response.results.map { it.toPokemon() }
    }

    override suspend fun getPokemon(url: String): PokemonDetails {
        return pokemonApi.getPokemon(url).toPokemon()
    }


}