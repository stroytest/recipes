package com.abstudio.recipes.domain

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.domain.entities.Category
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class CategoryStoreTest {

    private val db = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        RecipesDB::class.java
    ).build()

    private val underTest = db.categories()

    @Test
    fun insertAndDelete() {
        assertThat(underTest.loadAll(), isEmpty)

        val entity = Category(
            id = UUID.randomUUID().toString(),
            name = "This is a name",
            photoPath = "This is photo path",
        )

        underTest.insert(entity)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(entity))
        }

        underTest.delete(entity)

        assertThat(underTest.loadAll(), isEmpty)
    }
}