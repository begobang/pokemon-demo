package com.begobang.domain.business
/*

    We create business data class that corresponds to the response of data module's services.
    We need to have this Any objects so that we can use it in this module. Remember that
    domain cannot see data module or presentation module.

 */
data class PokemonsBusiness(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonItemBusiness>
)

data class PokemonItemBusiness(
    val name: String,
    val url: String,
    var imageUrl: String = ""
)

data class PokemonBusiness(
    val name: String,
    val url: String
)
