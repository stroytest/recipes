package com.abstudio.recipes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        var INSTANCE: RecipesDB? = null

        //db = RecipesDB.getDataBase(context = this)

        fun getDataBase(context: Context): RecipesDB? {
            if (INSTANCE == null) {
                synchronized(RecipesDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RecipesDB::class.java,
                        "recipeDB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}