package com.example.sphandroid.pokemon.domain.use_case

import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.repository.PokemonRepository

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