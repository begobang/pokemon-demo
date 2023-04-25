package com.begobang.data.di

import android.content.Context
import com.begobang.data.apiService.GetPokemonDetailApiService
import com.begobang.data.apiService.GetPokemonsApiService
import com.begobang.data.cache.AppDataBase
import com.begobang.data.cache.dao.PokemonDao
import com.begobang.data.cache.dao.PokemonDetailDao
import com.begobang.data.localDataSource.GetPokemonDetailLocalDataSource
import com.begobang.data.localDataSource.GetPokemonsLocalDataSource
import com.begobang.data.remoteDataSource.GetPokemonDetailRemoteDataSource
import com.begobang.data.remoteDataSource.GetPokemonsRemoteDataSource
import com.begobang.data.repositoryImpl.GetPokemonDetailRepositoryImpl
import com.begobang.data.repositoryImpl.GetPokemonsRepositoryImpl
import com.begobang.domain.GetPokemonDetailRepository
import com.begobang.domain.GetPokemonsRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    //Room
    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providePokemonsLocalDataSource(dao: PokemonDao): GetPokemonsLocalDataSource {
        return GetPokemonsLocalDataSource(dao)
    }

    @Singleton
    @Provides
    fun providePokemonDetailLocalDataSource(dao: PokemonDetailDao): GetPokemonDetailLocalDataSource {
        return GetPokemonDetailLocalDataSource(dao)
    }

    @Provides
    fun providePokemonDao(appDataBase: AppDataBase): PokemonDao {
        return appDataBase.pokemonDao()
    }

    @Provides
    fun providePokemonDetailDao(appDataBase: AppDataBase): PokemonDetailDao {
        return appDataBase.pokemonDetailDao()
    }

    //Repository

    @Provides
    @Singleton
    fun providePokemonsRepository(pokemonsRemoteDataSource: GetPokemonsRemoteDataSource, pokemonsLocalDataSource: GetPokemonsLocalDataSource): GetPokemonsRepository {
        return GetPokemonsRepositoryImpl(pokemonsRemoteDataSource, pokemonsLocalDataSource)
    }

    @Provides
    @Singleton
    fun providePokemonDetailRepository(pokemonDetailRemoteDataSource: GetPokemonDetailRemoteDataSource, pokemonDetailLocalDataSource: GetPokemonDetailLocalDataSource): GetPokemonDetailRepository {
        return GetPokemonDetailRepositoryImpl(pokemonDetailRemoteDataSource, pokemonDetailLocalDataSource)
    }

    //RemoteDataSource

    @Provides
    @Singleton
    fun providePokemonsRemoteDataSource(pokemonApiService: GetPokemonsApiService): GetPokemonsRemoteDataSource {
        return GetPokemonsRemoteDataSource(pokemonApiService)
    }

    @Provides
    @Singleton
    fun providePokemonDetailRemoteDataSource(pokemonDetailApiService: GetPokemonDetailApiService): GetPokemonDetailRemoteDataSource {
        return GetPokemonDetailRemoteDataSource(pokemonDetailApiService)
    }

    //Api Service

    @Provides
    @Singleton
    fun providePokemonsApiService(retrofit: Retrofit): GetPokemonsApiService {
        return createRetrofitImplementation(retrofit)
    }

    @Provides
    @Singleton
    fun providePokemonDetailApiService(retrofit: Retrofit): GetPokemonDetailApiService {
        return createRetrofitImplementation(retrofit)
    }

    //Retrofit

    @Provides
    @ApiEndPoint
    fun provideApiEndPoint(): String = "https://pokeapi.co/api/v2/"

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