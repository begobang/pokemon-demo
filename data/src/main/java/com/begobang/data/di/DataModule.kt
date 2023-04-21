package com.begobang.data.di

import com.begobang.data.GetPokemonApiService
import com.begobang.data.GetPokemonRemoteDataSource
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(pokemonApiService: GetPokemonApiService): GetPokemonRemoteDataSource {
        return GetPokemonRemoteDataSource(pokemonApiService)
    }

    @Provides
    @Singleton
    fun providePokemonApiService(retrofit: Retrofit): GetPokemonApiService {
        return createRetrofitImplementation(retrofit)
    }

    @Provides
    @ApiEndPoint
    fun provideApiEndPoint(): String = "https://pokeapi.co/api/v2"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Provides
    fun provideRestAdapter(@ApiEndPoint apiEndPoint: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiEndPoint)
            .client(okHttpClient)
            .addConverterFactory(createConverterFactory())
            .build()
    }

    private fun createConverterFactory(vararg jsonAdapters: com.squareup.moshi.JsonAdapter<Any>): Converter.Factory {
        return MoshiConverterFactory.create(
            Moshi.Builder().run {
                jsonAdapters.forEach { add(it) }
                build()
            }
        )
    }


    private inline fun <reified T> createRetrofitImplementation(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiEndPoint