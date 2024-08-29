package com.example.sphandroid.data.remote.dto


import com.example.sphandroid.domain.model.Pokemon
import com.example.sphandroid.domain.model.PokemonDetails
import com.google.gson.annotations.SerializedName


data class PokemonDetailsDto(
    @SerializedName("forms")
    val forms: List<Form>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("moves")
    val moves: List<Move>,
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: Species,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("types")
    val types: List<Type>,
    @SerializedName("weight")
    val weight: Int
) {
    
    data class Form(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    
    data class Move(
        @SerializedName("move")
        val move: Move,
        @SerializedName("version_group_details")
        val versionGroupDetails: List<VersionGroupDetail>
    ) {
        
        data class Move(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )

        
        data class VersionGroupDetail(
            @SerializedName("level_learned_at")
            val levelLearnedAt: Int,
            @SerializedName("move_learn_method")
            val moveLearnMethod: MoveLearnMethod,
            @SerializedName("version_group")
            val versionGroup: VersionGroup
        ) {
            
            data class MoveLearnMethod(
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
            )

            
            data class VersionGroup(
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
            )
        }
    }

    
    data class Species(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    
    data class Sprites(
        @SerializedName("back_default")
        val backDefault: String,
        @SerializedName("back_female")
        val backFemale: String,
        @SerializedName("back_shiny")
        val backShiny: String,
        @SerializedName("back_shiny_female")
        val backShinyFemale: String,
        @SerializedName("front_default")
        val frontDefault: String,
        @SerializedName("front_female")
        val frontFemale: String,
        @SerializedName("front_shiny")
        val frontShiny: String,
        @SerializedName("front_shiny_female")
        val frontShinyFemale: String,
    )

    
    data class Type(
        @SerializedName("slot")
        val slot: Int,
        @SerializedName("type")
        val type: Type
    ) {
        
        data class Type(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }
}

fun PokemonDetailsDto.toPokemon(): PokemonDetails {
    return PokemonDetails(
        name = name,
    )
}
