package com.example.sphandroid.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sphandroid.R
import com.example.sphandroid.domain.model.PokemonDetails

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonDetailsScreen(pokemonDetails: PokemonDetails) {
    val pokemonDetailsState by rememberSaveable { mutableStateOf(pokemonDetails) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(pokemonDetailsState.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp),
                    text = pokemonDetailsState.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Weight:${pokemonDetailsState.weight.toFloat() / 10}Kg")
                    Text(text = "Height:${pokemonDetailsState.height.toFloat() / 10}m")
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation =50.dp),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp),
                    text = stringResource(R.string.moves),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                        pokemonDetailsState.moves.forEach {
                            Box(modifier = Modifier
                                .padding(4.dp)
                                .background(Color.Gray, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                            ) {
                                Text(text = it)
                            }
                        }

                }
            }
        }
    }
}


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