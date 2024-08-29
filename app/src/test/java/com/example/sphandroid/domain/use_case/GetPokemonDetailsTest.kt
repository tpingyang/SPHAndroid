package com.example.sphandroid.domain.use_case

import com.example.sphandroid.data.repository.FakePokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class GetPokemonDetailsTest {
    private  lateinit var getPokemonDetails: GetPokemonDetails

    @Before
    fun setUp() {
        getPokemonDetails = GetPokemonDetails(FakePokemonRepository())
    }

    @Test
    fun `test capitalized name and moves`() = runBlocking    {
        val result = getPokemonDetails("url")
        assertThat(result.name).isEqualTo("Pikachu")
        assertThat(result.moves[0]).isEqualTo("Mega punch")
        assertThat(result.moves[1]).isEqualTo("Thunder")
    }


}