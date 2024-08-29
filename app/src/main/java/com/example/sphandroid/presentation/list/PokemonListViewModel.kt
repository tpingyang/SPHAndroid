package com.example.sphandroid.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails
import com.example.sphandroid.domain.use_case.FilterPokemon
import com.example.sphandroid.domain.use_case.GetPokemonDetails
import com.example.sphandroid.domain.use_case.GetPokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var _navigateToDetailsScreen = MutableStateFlow<PokemonDetails?>(null)
    var navigateToDetailsScreen: StateFlow<PokemonDetails?> = _navigateToDetailsScreen.asStateFlow()

    init {
        populateCurrency()
    }

    private fun populateCurrency() {
        viewModelScope.launch {
            originalPokemons = pokemonUseCase()
            pokemons.value = originalPokemons
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
            val result = getPokemonDetails(pokemon.url)
            _navigateToDetailsScreen.value = result
        }

    }

    private fun resetNavigation() {
        _navigateToDetailsScreen.value = null
    }

    @Composable
    fun toUiState(): PokemonListState {
        val currencies by pokemons.collectAsState()
        val searchString by searchString.collectAsState()
        val navigateToDetailsScreen by navigateToDetailsScreen.collectAsState()

        return PokemonListState(
            pokemons = currencies,
            searchString = searchString,
            clearSearch = ::clearSearch,
            onSearchStringChange = ::onSearchStringChange,
            onPokemonSelected = ::onPokemonSelected,
            navigateToDetailsScreen = navigateToDetailsScreen,
            resetNavigation = ::resetNavigation
        )
    }
}