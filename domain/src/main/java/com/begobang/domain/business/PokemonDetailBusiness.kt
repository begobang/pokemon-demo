package com.begobang.domain.business

/*

    We create business data class that corresponds to the response of data module's services.
    We need to have this Any objects so that we can use it in this module. Remember that
    domain cannot see data module or presentation module.
 */
data class PokemonDetailBusiness(
    val abilities: List<AbilitiesBusiness>,
    val baseExperience: Int,
    val gameIndices: List<GameIndicesBusiness>,
    val height: Int,
    val id: Int,
    val moves: List<MovesBusiness>,
    val name: String,
    val order: Int,
    val species: PokemonBusiness,
    val sprites: SpritesBusiness,
    val types: List<TypesBusiness>,
    val weight: Int

)

data class AbilitiesBusiness(
    val ability: PokemonBusiness,
    val isHidden: Boolean,
    val slot: Int
)

data class GameIndicesBusiness(
    val gameIndex: Int,
    val version: PokemonBusiness
)

data class MovesBusiness(
    val move: PokemonBusiness,
    val versionGroupDetails: List<VersionGroupDetailsBusiness>
)

data class VersionGroupDetailsBusiness(
    val levelLearnedAt: Int,
    val moveLearnMethod: PokemonBusiness,
    val versionGroup: PokemonBusiness
)

data class SpritesBusiness(
    val frontDefault: String,
    val other: OtherSpriteBusiness
)

data class OtherSpriteBusiness(
    val home: FrontSpriteBusiness,
    val officialArtwork: FrontSpriteBusiness
)

data class FrontSpriteBusiness(
    val frontDefault: String
)

data class TypesBusiness(
    val slot: Int,
    val type: PokemonBusiness
)
