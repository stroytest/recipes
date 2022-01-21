package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "categories")
data class Category(var name: String) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var photoPath: String = ""
}
