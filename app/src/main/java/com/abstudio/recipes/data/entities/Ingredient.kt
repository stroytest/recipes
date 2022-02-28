package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "ingredients")
data class Ingredient(var name: String) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var oldId: String = ""
}