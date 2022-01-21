package com.abstudio.recipes.domain

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abstudio.recipes.domain.entities.Category
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import org.junit.Before
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
    fun loadAllAndSortByName() {

        assertThat(underTest.loadAll(), isEmpty)

        val entityB = Category("B")
        underTest.insert(entityB)

        val entityA = Category("A")
        underTest.insert(entityA)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(2)))
            assertThat(it[0], equalTo(entityA))
        }
    }


    @Test
    fun insertAndDelete() {
        assertThat(underTest.loadAll(), isEmpty)

        val entity = Category("This is a name")

        underTest.insert(entity)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(entity))
        }

        underTest.delete(entity)

        assertThat(underTest.loadAll(), isEmpty)
    }


    @Test
    fun getById() {

        val entity = Category("This is a name")

        underTest.insert(entity)

        val fromDB: Category = underTest.findById(entity.id)

        assertThat(fromDB, equalTo(entity))
    }

    @Test
    fun update() {

        val entity = Category("This is a name")

        underTest.insert(entity)

        val updated: Category = underTest.findById(entity.id)

        updated.name = "This is new"
        updated.photoPath = "So is this"

        underTest.update(updated)

        underTest.loadAll().let {
            assertThat(it, hasSize(equalTo(1)))
            assertThat(it[0], equalTo(updated))
        }
    }
}