package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dictionary")
class AutoCompletePhrase(var phrase: String): BaseEntity() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
}