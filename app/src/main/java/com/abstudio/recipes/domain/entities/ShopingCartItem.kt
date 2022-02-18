package com.abstudio.recipes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shoping_cart_item")
class ShopingCartItem(val ingredientId: String, val amountId: String) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var bought: Boolean = false
}