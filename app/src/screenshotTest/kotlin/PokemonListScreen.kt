import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.presentation.list.PokemonListScreen
import com.example.sphandroid.pokemon.presentation.list.PokemonListState


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