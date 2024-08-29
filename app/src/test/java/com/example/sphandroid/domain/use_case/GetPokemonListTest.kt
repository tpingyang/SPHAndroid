package com.example.sphandroid.domain.use_case

import com.example.sphandroid.data.repository.FakePokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class GetPokemonListTest {
    private  lateinit var getPokemonList: GetPokemonList

    @Before
    fun setUp() {
        getPokemonList = GetPokemonList(FakePokemonRepository())
    }

    @Test
    fun `test capitalized name`() = runBlocking    {
        val result = getPokemonList()
        assertThat(result.size).isEqualTo(5)
        assertThat(result[0].name).isEqualTo("Pikachu")
    }


}