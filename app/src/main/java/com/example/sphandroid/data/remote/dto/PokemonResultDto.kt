package com.example.sphandroid.data.remote.dto

import com.example.sphandroid.domain.model.Pokemon

data class PokemonResultDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonDto>,
)


