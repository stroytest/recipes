package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "amounts")
data class Amount(
    var name: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {
    var oldId: String = ""
}