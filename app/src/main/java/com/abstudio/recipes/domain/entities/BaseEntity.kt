package com.abstudio.recipes.domain.entities

import androidx.room.PrimaryKey
import java.util.*

open class BaseEntity {
    //@PrimaryKey
    //var id: String = UUID.randomUUID().toString()
    var oldId: Int = 0
}