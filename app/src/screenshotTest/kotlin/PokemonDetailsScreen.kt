import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sphandroid.pokemon.domain.model.PokemonDetails
import com.example.sphandroid.pokemon.presentation.details.PokemonDetailsScreen

@Preview
@Composable
fun PreviewPokemonDetailsScreen() {
    PokemonDetailsScreen(
        pokemonDetails = PokemonDetails(
            name = "Bulbasaur",
            weight = 100,
            height = 200,
            moves = listOf("Bite", "Razer Leaf", "Solarbeam", "Leech Seed"),
            imageUrl = "https://pokeapi.co/media/sprites/items/master-ball.png"
        )
    )
}