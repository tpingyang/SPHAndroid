package com.example.sphandroid.pokemon.domain.use_case

import com.example.sphandroid.pokemon.domain.model.Pokemon

class FilterPokemon {
    operator fun invoke(searchTerm: String, list: List<Pokemon>): List<Pokemon> {
        return list.filter {
            it.name.contains(searchTerm, true)
        }
    }
}