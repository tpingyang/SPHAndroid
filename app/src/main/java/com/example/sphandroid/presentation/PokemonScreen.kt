package com.example.sphandroid.presentation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import com.example.sphandroid.presentation.details.PokemonDetailsScreen
import com.example.sphandroid.presentation.list.PokemonListScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun PokemonScreen() {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = { PokemonListScreen(navigator = navigator) },
        detailPane = { PokemonDetailsScreen(navigator = navigator) }
    )
}