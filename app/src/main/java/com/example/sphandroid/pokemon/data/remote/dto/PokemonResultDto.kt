package com.example.sphandroid.pokemon.data.remote.dto

data class PokemonResultDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonDto>,
)


