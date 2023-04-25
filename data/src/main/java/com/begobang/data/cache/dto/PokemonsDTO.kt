package com.begobang.data.cache.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    In the data classes annotated as Entity we will define a different table for our SQL DataBase.
    We will have to provide the exact column names we desire and which will be the primary key.
    This primary key is unique and it defines the identifier of each item or row.
 */
@Entity(tableName = "pokemons")
data class PokemonsDTO(
    @PrimaryKey @ColumnInfo("name") val name: String = "",
    @ColumnInfo("url") val url: String,
    @ColumnInfo("imageUrl") val imageUrl: String = ""

)