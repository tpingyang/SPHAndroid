package com.example.sphandroid.pokemon.presentation.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sphandroid.R
import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.model.PokemonDetails


data class PokemonListState(
    val pokemons: List<Pokemon>,
    val searchString: String,
    val clearSearch: () -> Unit,
    val onSearchStringChange: (newString: String) -> Unit,
    val onPokemonSelected: (pokemon: Pokemon) -> Unit,
    val resetNavigation: () -> Unit,
    val isLoading: Boolean = false,
    val navigateToDetailsScreen: PokemonDetails? = null,
)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun PokemonListScreen(
    navigator: ThreePaneScaffoldNavigator<Any>,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val pokemonListState = viewModel.toUiState()

    LoadingDialog(isShowingDialog = pokemonListState.isLoading)

    LaunchedEffect(pokemonListState.navigateToDetailsScreen) {
        pokemonListState.navigateToDetailsScreen?.let {
            pokemonListState.resetNavigation()
            navigator.navigateTo(
                pane = ListDetailPaneScaffoldRole.Detail,
                content = it
            )
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
            placeholder = { Text(text = stringResource(R.string.search)) }
        )
        if (pokemonListState.pokemons.isNotEmpty()) {
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
                    HorizontalDivider(color = Color.LightGray)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.no_results),
                    fontSize = 16.sp,
                )
            }
        }
    }
}

@Composable
fun DialogContent() {
    Box(
        modifier = Modifier
            .size(76.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(
                    Alignment.Center
                ),
            color = Color.Red
        )
    }
}

@Composable
fun LoadingDialog(
    isShowingDialog: Boolean,
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false
) {
    if (isShowingDialog) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            DialogContent()
        }
    }
}


@Preview
@Composable
fun PreviewPokemonListScreen() {
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
fun PreviewPokemonListScreenEmpty() {
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