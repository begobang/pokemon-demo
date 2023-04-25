package com.begobang.data.cache.mapper

import com.begobang.data.cache.dto.*
import com.begobang.domain.business.*

fun PokemonDTO.toDomain(): PokemonDetailBusiness {
    return PokemonDetailBusiness(
        abilities.map { it.toDomain() },
        baseExperience,
        gameIndices.map { it.toDomain() },
        height,
        id,
        moves.map { it.toDomain() },
        name,
        order,
        PokemonBusiness(species.name, species.url),
        sprites.toDomain(),
        types.map { it.toDomain() },
        weight
    )
}

fun AbilitiesDTO.toDomain(): AbilitiesBusiness {
    return AbilitiesBusiness(
        ability = PokemonBusiness(ability.name, ability.url),
        isHidden = is_hidden,
        slot = slot
    )
}

fun AbilitiesBusiness.toDTO(): AbilitiesDTO {
    return AbilitiesDTO(
        ability = PokemonsDTO(ability.name, ability.url),
        is_hidden = isHidden,
        slot = slot
    )
}

fun GameIndicesDTO.toDomain(): GameIndicesBusiness {
    return GameIndicesBusiness(
        gameIndex, PokemonBusiness(version.name, version.url)
    )
}

fun GameIndicesBusiness.toDTO(): GameIndicesDTO {
    return GameIndicesDTO(
        gameIndex, PokemonsDTO(version.name, version.url)
    )
}

fun MovesDTO.toDomain(): MovesBusiness {
    return MovesBusiness(
        PokemonBusiness(move.name, move.url),
        versionGroupDetails.map {
            VersionGroupDetailsBusiness(
                it.levelLearnedAt,
                PokemonBusiness(it.moveLearnMethod.name, it.moveLearnMethod.url),
                PokemonBusiness(it.versionGroup.name, it.versionGroup.url)
            )
        }

    )
}

fun MovesBusiness.toDTO(): MovesDTO {
    return MovesDTO(
        PokemonsDTO(move.name, move.url),
        versionGroupDetails.map {
            VersionGroupDetailsDTO(
                it.levelLearnedAt,
                PokemonsDTO(it.moveLearnMethod.name, it.moveLearnMethod.url),
                PokemonsDTO(it.versionGroup.name, it.versionGroup.url)
            )
        }
    )
}

fun SpritesDTO.toDomain(): SpritesBusiness {
    return SpritesBusiness(
        frontDefault,
        OtherSpriteBusiness(
            FrontSpriteBusiness(other.home.frontDefault),
            FrontSpriteBusiness(other.officialArtwork.frontDefault)
        )
    )
}

fun SpritesBusiness.toDTO(): SpritesDTO {
    return SpritesDTO(
        frontDefault,
        OtherSpriteDTO(
            FrontSpriteDTO(other.home.frontDefault),
            FrontSpriteDTO(other.officialArtwork.frontDefault)
        )
    )
}

fun TypesDTO.toDomain(): TypesBusiness {
    return TypesBusiness(
        slot, PokemonBusiness(type.name, type.url)
    )
}

fun TypesBusiness.toDTO(): TypesDTO {
    return TypesDTO(
        slot, PokemonsDTO(type.name, type.url)
    )
}

fun PokemonDetailBusiness.toDTO(): PokemonDTO {
    return PokemonDTO(
        abilities.map { it.toDTO() },
        baseExperience,
        gameIndices.map { it.toDTO() },
        height,
        id,
        moves.map { it.toDTO() },
        name,
        order,
        PokemonsDTO(species.name, species.url),
        sprites.toDTO(),
        types.map { it.toDTO() },
        weight
    )

}