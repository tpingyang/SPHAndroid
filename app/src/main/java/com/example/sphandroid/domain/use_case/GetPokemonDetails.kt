package com.example.sphandroid.domain.use_case

import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.domain.repository.pokemonRepository

class GetPokemonDetails(
    private val pokemonRepository: pokemonRepository
) {
    suspend operator fun invoke(url: String): PokemonDetails {
        val result = pokemonRepository.getPokemon(url)
        return PokemonDetails(
            weight = result.weight,
            height = result.height,
            imageUrl = result.imageUrl,
            name = result.name.replaceFirstChar(kotlin.Char::titlecase),
            moves = result.moves.map {
                it.replace("-", " ").replaceFirstChar(kotlin.Char::titlecase)
            }
        )
    }
}