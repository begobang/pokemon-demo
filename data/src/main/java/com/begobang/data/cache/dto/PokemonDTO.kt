package com.begobang.data.cache.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonDTO (
    @ColumnInfo("abilities") val abilities: List<AbilitiesDTO>,
    @ColumnInfo("base_experience") val baseExperience: Int,
    @ColumnInfo("game_indices") val gameIndices: List<GameIndicesDTO>,
    @ColumnInfo("height") val height: Int,
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("moves") val moves: List<MovesDTO>,
    @PrimaryKey @ColumnInfo("name") val name: String,
    @ColumnInfo("order") val order: Int,
    @ColumnInfo("species") val species: PokemonsDTO,
    @ColumnInfo("sprites") val sprites: SpritesDTO,
    @ColumnInfo("types") val types: List<TypesDTO>,
    @ColumnInfo("weight") val weight: Int
)

@Entity(tableName = "abilities")
data class AbilitiesDTO(
    @ColumnInfo("ability") val ability: PokemonsDTO,
    @ColumnInfo("is_hidden") val is_hidden: Boolean,
    @PrimaryKey @ColumnInfo("slot") val slot: Int
)

@Entity(tableName = "gameIndices")
data class GameIndicesDTO(
    @ColumnInfo("game_index") val gameIndex: Int,
    @ColumnInfo("version") val version: PokemonsDTO
)

@Entity(tableName = "moves")
data class MovesDTO(
    @ColumnInfo("move") val move: PokemonsDTO,
    @ColumnInfo("version_group_details") val versionGroupDetails: List<VersionGroupDetailsDTO>
)

@Entity(tableName = "version_group_details")
data class VersionGroupDetailsDTO(
    @ColumnInfo("level_learned_at") val levelLearnedAt: Int,
    @ColumnInfo("move_learn_method") val moveLearnMethod: PokemonsDTO,
    @ColumnInfo("version_group") val versionGroup: PokemonsDTO
)

@Entity(tableName = "sprites")
data class SpritesDTO(
    @ColumnInfo("front_default") val frontDefault: String,
    @ColumnInfo("other") val other: OtherSpriteDTO
)

@Entity(tableName = "other")
data class OtherSpriteDTO(
    @ColumnInfo("home") val home: FrontSpriteDTO,
    @ColumnInfo("official-artwork") val officialArtwork: FrontSpriteDTO
)

@Entity(tableName = "home")
data class FrontSpriteDTO(
    @ColumnInfo("front_default") val frontDefault: String
)

@Entity(tableName = "types")
data class TypesDTO(
    @ColumnInfo("slot") val slot: Int,
    @ColumnInfo("type") val type: PokemonsDTO
)

