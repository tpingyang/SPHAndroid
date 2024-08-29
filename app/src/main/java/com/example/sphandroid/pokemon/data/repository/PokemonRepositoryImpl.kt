package com.example.sphandroid.pokemon.data.repository

import com.example.sphandroid.pokemon.data.remote.PokemonApi
import com.example.sphandroid.pokemon.data.remote.dto.toPokemon
import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.model.PokemonDetails
import com.example.sphandroid.pokemon.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val pokemonApi: PokemonApi
): PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        val response = pokemonApi.getPokemons()
        return response.results.map { it.toPokemon() }
    }

    override suspend fun getPokemon(url: String): PokemonDetails {
        return pokemonApi.getPokemon(url).toPokemon()
    }


}