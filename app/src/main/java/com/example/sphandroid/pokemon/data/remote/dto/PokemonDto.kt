package com.example.sphandroid.pokemon.data.remote.dto

import com.example.sphandroid.pokemon.domain.model.Pokemon

data class PokemonDto(
    val name: String,
    val url: String,
) {
    companion object {
        fun toPokemonDto(pokemon: Pokemon): PokemonDto {
            return PokemonDto(
                name = pokemon.name,
                url = pokemon.url,
            )
        }
    }
}

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        url = url,
    )
}


