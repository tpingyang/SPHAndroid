package com.example.sphandroid.pokemon.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class PokemonDetails(
    val name: String,
    val weight: Int,
    val height: Int,
    val moves: List<String>,
    val imageUrl: String,
): Parcelable

