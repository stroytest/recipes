package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "categories")
data class Category(
    var name: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {
    var photoPath: String = ""
    var oldId: String = ""
}
