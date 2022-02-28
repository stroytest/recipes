package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "recipes")
data class Recipe(var name: String) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var photoPath: String = ""
    var cooking: String = ""
    var comment: String = ""
    var oldId: String = ""
}