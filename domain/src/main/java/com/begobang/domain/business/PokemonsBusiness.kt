package com.begobang.domain.business

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
