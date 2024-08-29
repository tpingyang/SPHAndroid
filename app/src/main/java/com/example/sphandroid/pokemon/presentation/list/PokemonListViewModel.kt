package com.example.sphandroid.pokemon.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.model.PokemonDetails
import com.example.sphandroid.pokemon.domain.use_case.FilterPokemon
import com.example.sphandroid.pokemon.domain.use_case.GetPokemonDetails
import com.example.sphandroid.pokemon.domain.use_case.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: GetPokemonList,
    private val getPokemonDetails: GetPokemonDetails,
    private val filterPokemon: FilterPokemon,
) : ViewModel() {
    private var originalPokemons: List<Pokemon> = emptyList()
    private val pokemons = MutableStateFlow<List<Pokemon>>(emptyList())

    private val searchString = MutableStateFlow("")
    private val isLoading = MutableStateFlow(false)

    private var navigateToDetailsScreen = MutableStateFlow<PokemonDetails?>(null)

    init {
        populatePokemon()
    }

    private fun populatePokemon() {
        viewModelScope.launch {
            isLoading.value = true
            try {

                originalPokemons = pokemonUseCase()
                pokemons.value = originalPokemons
            } catch (_: Exception) {
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun onSearchStringChange(newString: String) {
        searchString.value = newString
        if (newString.isEmpty()) {
            pokemons.value = originalPokemons
            return
        }
        pokemons.value = filterPokemon(newString, originalPokemons)
    }

    private fun clearSearch() {
        onSearchStringChange("")
    }

    private fun onPokemonSelected(pokemon: Pokemon) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getPokemonDetails(pokemon.url)
                navigateToDetailsScreen.value = result
            } catch (_: Exception) {
            } finally {
                isLoading.value = false
            }
        }

    }

    private fun resetNavigation() {
        navigateToDetailsScreen.value = null
    }

    @Composable
    fun toUiState(): PokemonListState {
        val currencies by pokemons.collectAsState()
        val searchString by searchString.collectAsState()
        val navigateToDetailsScreen by navigateToDetailsScreen.collectAsState()
        val isLoading by isLoading.collectAsState()

        return PokemonListState(
            pokemons = currencies,
            searchString = searchString,
            clearSearch = ::clearSearch,
            onSearchStringChange = ::onSearchStringChange,
            onPokemonSelected = ::onPokemonSelected,
            navigateToDetailsScreen = navigateToDetailsScreen,
            resetNavigation = ::resetNavigation,
            isLoading = isLoading
        )
    }
}