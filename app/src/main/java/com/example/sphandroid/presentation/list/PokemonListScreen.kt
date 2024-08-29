package com.example.sphandroid.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sphandroid.PokemonDetailsRoute
import com.example.sphandroid.R
import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails


data class PokemonListState(
    val pokemons: List<Pokemon>,
    val searchString: String,
    val clearSearch: () -> Unit,
    val onSearchStringChange: (newString: String) -> Unit,
    val onPokemonSelected: (pokemon: Pokemon) -> Unit,
    val resetNavigation: () -> Unit,
    val navigateToDetailsScreen: PokemonDetails? = null,
)

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val pokemonListState = viewModel.toUiState()
    LaunchedEffect(pokemonListState.navigateToDetailsScreen) {
        pokemonListState.navigateToDetailsScreen?.let {
            pokemonListState.resetNavigation()
            navController.navigate(PokemonDetailsRoute(it))
        }
    }
    PokemonListScreen(pokemonListState)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PokemonListScreen(pokemonListState: PokemonListState) {
    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pokemonListState.searchString,
            onValueChange = pokemonListState.onSearchStringChange,
            placeholder = {Text(text = stringResource(R.string.search))}
        )
        LazyColumn(
            modifier = Modifier
                .background(Color.White),

            ) {
            items(pokemonListState.pokemons) { pokemon ->
                Row(
                    Modifier
                        .height(60.dp)
                        .padding(horizontal = 12.dp)
                        .clickable { pokemonListState.onPokemonSelected(pokemon) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(color = Color.DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${pokemon.name[0]}",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                    Text(
                        text = pokemon.name,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                            )
                            .weight(1f)
                    )


                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = stringResource(R.string.next),
                        )
                    }
                }
                Divider(color = Color.LightGray)
            }
        }
    }
}


@Preview
@Composable
fun PreviewCurrencyScreen() {
    PokemonListScreen(
        pokemonListState = PokemonListState(
            pokemons = listOf(
                Pokemon("Hunter", ""),
                Pokemon("Gengar", ""),
                Pokemon("Onix", ""),
                Pokemon("Drowzee", ""),
                Pokemon("Hypno", ""),
            ),
            searchString = "Search",
            clearSearch = {},
            onSearchStringChange = {},
            onPokemonSelected = {},
            resetNavigation = {}
        )
    )
}

@Preview
@Composable
fun PreviewCurrencyScreenEmpty() {
    PokemonListScreen(
        pokemonListState = PokemonListState(
            pokemons = emptyList(),
            searchString = "Search",
            clearSearch = {},
            onSearchStringChange = {},
            onPokemonSelected = {},
            resetNavigation = {}
        )
    )
}