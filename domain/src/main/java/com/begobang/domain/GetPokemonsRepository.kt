package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.PokemonsBusiness
import com.begobang.domain.failure.Failure

/*
    Repository interfaces are created to establish a connection between the data and domain modules.
    We create an interface so that we can make all the logic needed in the data module's
    RepositoryImpl and when we provide the repository in the DataModule we will
    return the implementation which extends
    the domain Repository interface.
 */
interface GetPokemonsRepository {
    suspend fun getPokemons(): Either<Failure, PokemonsBusiness?>
}