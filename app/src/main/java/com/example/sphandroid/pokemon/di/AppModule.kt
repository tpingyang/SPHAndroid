package com.example.sphandroid.pokemon.di

import android.app.Application
import com.example.sphandroid.pokemon.data.remote.PokemonApi
import com.example.sphandroid.pokemon.data.repository.PokemonRepositoryImpl
import com.example.sphandroid.pokemon.domain.repository.PokemonRepository
import com.example.sphandroid.pokemon.domain.use_case.FilterPokemon
import com.example.sphandroid.pokemon.domain.use_case.GetPokemonDetails
import com.example.sphandroid.pokemon.domain.use_case.GetPokemonList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
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
    fun okHttpClient(context: Application): OkHttpClient {
        val levelType: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
            .addNetworkInterceptor(CacheInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetPokemonList(pokemonRepository: PokemonRepository): GetPokemonList {
        return GetPokemonList(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetails(pokemonRepository: PokemonRepository): GetPokemonDetails {
        return GetPokemonDetails(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideFilterPokemon(): FilterPokemon {
        return FilterPokemon()
    }
}


class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}