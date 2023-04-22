package com.begobang.data.response

import com.begobang.domain.business.PokemonBusiness
import com.begobang.domain.business.PokemonsBusiness
import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonResponse>
)

data class PokemonResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)


fun PokemonsResponse.toDomain(): PokemonsBusiness {
    return PokemonsBusiness(count, next, previous, results.map { it.toDomain() })
}

fun PokemonResponse.toDomain(): PokemonBusiness {
    return PokemonBusiness(name, url)
}