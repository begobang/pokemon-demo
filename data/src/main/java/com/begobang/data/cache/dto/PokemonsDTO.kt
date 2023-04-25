package com.begobang.data.cache.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonsDTO(
    @PrimaryKey @ColumnInfo("name") val name: String = "",
    @ColumnInfo("url") val url: String,
    @ColumnInfo("imageUrl") val imageUrl: String = ""

)