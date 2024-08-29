package com.example.sphandroid.data.repository

import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.domain.repository.PokemonRepository

class FakePokemonRepository : PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        return listOf(
            Pokemon(name = "pikachu", url = ""),
            Pokemon(name = "charmander", url = ""),
            Pokemon(name = "squitle", url = ""),
            Pokemon(name = "charizard", url = ""),
            Pokemon(name = "caterpie", url = ""),
        )
    }

    override suspend fun getPokemon(url: String): PokemonDetails {
        return PokemonDetails(
            name = "pikachu",
            weight = 60,
            height = 4,
            moves = listOf("mega-punch", "thunder"),
            imageUrl = "imageurl"
        )
    }
}