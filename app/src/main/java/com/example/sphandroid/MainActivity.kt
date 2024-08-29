package com.example.sphandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.presentation.CustomNavType
import com.example.sphandroid.presentation.details.PokemonDetailsScreen
import com.example.sphandroid.presentation.list.PokemonListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = PokemonListRoute
                    ) {
                        composable<PokemonListRoute> {
                            PokemonListScreen(navController)
                        }

                        composable<PokemonDetailsRoute>(
                            typeMap = mapOf(
                                typeOf<PokemonDetails>() to CustomNavType.PokemonDetailsType
                            )
                        ) {
                            val args = it.toRoute<PokemonDetailsRoute>()
                            PokemonDetailsScreen(pokemonDetails = args.pokemonDetails)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object PokemonListRoute

@Serializable
data class PokemonDetailsRoute(
    val pokemonDetails: PokemonDetails
)