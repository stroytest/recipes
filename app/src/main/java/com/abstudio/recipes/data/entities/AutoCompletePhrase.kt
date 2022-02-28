package com.abstudio.recipes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dictionary")
class AutoCompletePhrase(var phrase: String) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var oldId: String = ""
}