package com.example.sphandroid.domain.use_case

import com.example.sphandroid.pokemon.domain.model.Pokemon
import com.example.sphandroid.pokemon.domain.use_case.FilterPokemon
import com.google.common.truth.Truth
import org.junit.Test

class FilterPokemonTest {
    private val filterPokemon = FilterPokemon()
    val pokemonList = listOf(
        Pokemon(name = "Pikachu", url = ""),
        Pokemon(name = "Charmander", url = ""),
        Pokemon(name = "Squitle", url = ""),
        Pokemon(name = "Charizard", url = ""),
        Pokemon(name = "Caterpie", url = ""),
    )
    @Test
    fun `filter with result`() {
        val result = filterPokemon("Char", pokemonList)
        Truth.assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun `filter without result`() {

        val result = filterPokemon("Bul", pokemonList)
        Truth.assertThat(result).isEmpty()
    }
}