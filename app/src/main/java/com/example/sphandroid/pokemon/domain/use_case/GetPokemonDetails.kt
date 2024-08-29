package com.example.sphandroid.pokemon.domain.use_case

import com.example.sphandroid.pokemon.domain.model.PokemonDetails
import com.example.sphandroid.pokemon.domain.repository.PokemonRepository

class GetPokemonDetails(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(url: String): PokemonDetails {
        val result = pokemonRepository.getPokemon(url)
        return PokemonDetails(
            weight = result.weight,
            height = result.height,
            imageUrl = result.imageUrl,
            name = result.name.replaceFirstChar(Char::titlecase),
            moves = result.moves.map {
                it.replace("-", " ").replaceFirstChar(Char::titlecase)
            }
        )
    }
}