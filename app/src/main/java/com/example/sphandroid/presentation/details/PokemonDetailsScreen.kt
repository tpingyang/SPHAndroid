package com.example.sphandroid.presentation.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sphandroid.domain.model.PokemonDetails

@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {
    Text(text = pokemonDetails.name)
}