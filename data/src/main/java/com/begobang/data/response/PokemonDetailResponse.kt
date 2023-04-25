package com.begobang.data.response

import com.begobang.domain.business.*
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("abilities") val abilities: List<AbilitiesResponse>,
    @SerializedName("base_experience") val base_experience: Int,
    @SerializedName("game_indices") val game_indices: List<GameIndicesResponse>,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("moves") val moves: List<MovesResponse>,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("species") val species: PokemonResponse,
    @SerializedName("sprites") val sprites: SpritesResponse,
    @SerializedName("types") val types: List<TypesResponse>,
    @SerializedName("weight") val weight: Int

)

data class AbilitiesResponse(
    @SerializedName("ability") val ability: PokemonResponse,
    @SerializedName("is_hidden") val is_hidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class GameIndicesResponse(
    @SerializedName("game_index") val game_index: Int,
    @SerializedName("version") val version: PokemonResponse,
)

data class MovesResponse(
    @SerializedName("move") val move: PokemonResponse,
    @SerializedName("version_group_details") val version_group_details: List<VersionGroupDetailsResponse>
)

data class VersionGroupDetailsResponse(
    @SerializedName("level_learned_at") val level_learned_at: Int,
    @SerializedName("move_learn_method") val move_learn_method: PokemonResponse,
    @SerializedName("version_group") val version_group: PokemonResponse
)

data class SpritesResponse(
    @SerializedName("front_default") val front_default: String,
    @SerializedName("other") val other: OtherSpriteResponse
)

data class OtherSpriteResponse(
    @SerializedName("home") val home: FrontSpriteResponse,
    @SerializedName("official-artwork") val `official-artwork`: FrontSpriteResponse
)

data class FrontSpriteResponse(
    @SerializedName("front_default") val front_default: String
)

data class TypesResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: PokemonResponse
)

fun PokemonDetailResponse.toDomain(): PokemonDetailBusiness {
    return PokemonDetailBusiness(
        abilities.map { it.toDomain() },
        base_experience,
        game_indices.map { it.toDomain() },
        height,
        id,
        moves.map { it.toDomain() },
        name,
        order,
        species.toDomain(),
        sprites.toDomain(),
        types.map { it.toDomain() },
        weight)
}

fun AbilitiesResponse.toDomain(): AbilitiesBusiness {
    return AbilitiesBusiness(ability.toDomain(), is_hidden, slot)
}

fun GameIndicesResponse.toDomain(): GameIndicesBusiness {
    return GameIndicesBusiness(game_index, version.toDomain())
}

fun MovesResponse.toDomain(): MovesBusiness {
    return MovesBusiness(
        move.toDomain(),
        version_group_details.map {
            VersionGroupDetailsBusiness(it.level_learned_at,
                it.move_learn_method.toDomain(),
                it.version_group.toDomain())
        }
    )
}

fun SpritesResponse.toDomain(): SpritesBusiness{
    return SpritesBusiness(
        front_default, other.toDomain()
    )
}

fun OtherSpriteResponse.toDomain(): OtherSpriteBusiness {
    return OtherSpriteBusiness(FrontSpriteBusiness(home.front_default), FrontSpriteBusiness(`official-artwork`.front_default))
}

fun TypesResponse.toDomain(): TypesBusiness {
    return TypesBusiness(slot, type.toDomain())
}




