package com.example.sphandroid.di

import com.example.sphandroid.data.remote.PokemonApi
import com.example.sphandroid.data.repository.PokemonRepositoryImpl
import com.example.sphandroid.domain.repository.pokemonRepository
import com.example.sphandroid.domain.use_case.GetPokemonDetails
import com.example.sphandroid.domain.use_case.GetPokemonList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(okHttpClient: OkHttpClient): PokemonApi {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    fun okHttpClient(): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): pokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun proviewGetPokemonList(pokemonRepository: pokemonRepository): GetPokemonList {
        return GetPokemonList(pokemonRepository)
    }

    @Provides
    @Singleton
    fun proviewGetPokemonDetails(pokemonRepository: pokemonRepository): GetPokemonDetails {
        return GetPokemonDetails(pokemonRepository)
    }
}