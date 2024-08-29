package com.example.sphandroid.domain.use_case

import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.repository.PokemonRepository

class GetPokemonList(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): List<Pokemon> {
        val result = pokemonRepository.getPokemons()
        return result.map { pokemon ->
            Pokemon(pokemon.name.replaceFirstChar(Char::titlecase), pokemon.url)
        }
    }
}