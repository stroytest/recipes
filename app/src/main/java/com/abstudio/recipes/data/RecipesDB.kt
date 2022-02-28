package com.abstudio.recipes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abstudio.recipes.data.entities.*
import com.abstudio.recipes.data.stores.*


@Database(
    entities =
    [Category::class,
        Recipe::class,
        Ingredient::class,
        Amount::class,
        CategoryRecipeJoin::class,
        RecipeIngredientJoin::class,
        AutoCompletePhrase::class,
        ShopingCartItem::class],
    version = 1
)
abstract class RecipesDB : RoomDatabase() {

    abstract fun categories(): CategoryStore

    abstract fun recipes(): RecipeStore

    abstract fun ingredients(): IngredientStore

    abstract fun amounts(): AmountStore

    abstract fun category_recipe_joins(): CategoryRecipeJoinStore

    abstract fun recipe_ingredient_joins(): RecipeIngredientJoinStore

    abstract fun auto_complete_phrase(): AutoCompletePhraseStore

    abstract fun shoping_cart_item(): ShopingCartItemStore
}