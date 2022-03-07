package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dictionary")
data class AutoCompletePhrase(
    var phrase: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {
    var oldId: String = ""
}