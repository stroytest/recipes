package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey



    @Entity(tableName = "categories")
    data class Category(
        @PrimaryKey val id: String,
        val name: String,
        val photoPath: String,
    )
