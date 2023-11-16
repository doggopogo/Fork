package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item

@Dao
interface ItemDao {
    // Insert a single item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(items: List<Item>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get an item by id
    @Query("SELECT * FROM Item WHERE id = :id")
    suspend fun getItemById(id: Int): Item?

    // Get all items by name
    @Query("SELECT * FROM Item WHERE name LIKE :name")
    suspend fun getItemByName(name: String): List<Item>

    // Get all items by typeId
    @Query("SELECT * FROM Item WHERE typeId = :typeId")
    suspend fun getItemByTypeId(typeId: Int): List<Item>

    // Get all items by hydrationIndexId
    @Query("SELECT * FROM Item WHERE hydrationIndexId = :hydrationIndexId")
    suspend fun getItemByHydrationIndexId(hydrationIndexId: Int): List<Item>

    // Get all items where hydrationIndexId is not null
    @Query("SELECT * FROM Item WHERE hydrationIndexId IS NOT NULL")
    suspend fun getItemWithHydrationIndex(): List<Item>

    // Get all items by alcoholContentId
    @Query("SELECT * FROM Item WHERE alcoholContentId = :alcoholContentId")
    suspend fun getItemByAlcoholContentId(alcoholContentId: Int): List<Item>

    // Get all items where alcoholContentId is not null
    @Query("SELECT * FROM Item WHERE alcoholContentId IS NOT NULL")
    suspend fun getItemWithAlcoholContent(): List<Item>

    // Get all items
    @Query("SELECT * FROM Item")
    suspend fun getAllItems(): List<Item>

    // Delete an item by id
    @Query("DELETE FROM Item WHERE id = :id")
    suspend fun deleteItemById(id: Int): Int   // by convention, deleting a row returns the number of affected rows as an Int

    // Update an item by id
    @Query("UPDATE Item SET name = :name, description = :description, typeId = :typeId, hydrationIndexId = :hydrationIndexId, alcoholContentId = :alcoholContentId WHERE id = :id")
    suspend fun updateItemById(
        id: Int,
        name: String,
        description: String?,
        typeId: Int,
        hydrationIndexId: Int?,
        alcoholContentId: Int?
    ): Int  // by convention, updating a row returns the number of affected rows as an Int
}