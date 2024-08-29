package com.example.sphandroid.domain.use_case

import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.domain.repository.pokemonRepository

class GetPokemonDetails(
    private val pokemonRepository: pokemonRepository
) {
    suspend operator fun invoke(url: String): PokemonDetails {
        return pokemonRepository.getPokemon(url)

    }
}