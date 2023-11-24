package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemServing
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithServings
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving

@Dao
interface ServingDao {
    // Insert a single serving
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServing(serving: Serving): Long

    // Insert multiple servings
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllServings(servings: List<Serving>): List<Long>

    // Get serving by id
    @Query("SELECT * FROM Serving WHERE id = :id")
    suspend fun getServingById(id: Int): Serving?

    // Get all servings by name
    @Query("SELECT * FROM Serving WHERE name = :name")
    suspend fun getServingByName(name: String): List<Serving>

    // Get all servings by unitId
    @Query("SELECT * FROM Serving WHERE unitId = :unitId")
    suspend fun getServingByUnitId(unitId: Int): List<Serving>

    // Get all servings
    @Query("SELECT * FROM Serving")
    suspend fun getAllServings(): List<Serving>

    // Delete serving by id
    @Query("DELETE FROM Serving WHERE id = :id")
    suspend fun deleteServingById(id: Int): Int

    // Update a serving by id
    @Query("UPDATE Serving SET size = :size, name = :name, amount = :amount, unitId = :unitId WHERE id = :id")
    suspend fun updateServingById(
        id: Int,
        size: Int,
        name: String,
        amount: Int,
        unitId: Int
    ): Int

    // Checks if an entry already exists based on the size and name.
    // Returns id if it exists, null if it doesn't
    @Query("SELECT id FROM Serving WHERE size = :size AND name = :name LIMIT 1")
    suspend fun findServingIdByNameAndSize(name: String, size: Int): Int?
}

@Dao
interface ItemServingDao {
    // Insert a new ItemServing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemServing(itemServing: ItemServing): Long

    // Insert multiple ItemServings
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemServings(itemServings: List<ItemServing>): List<Long>

    // Get ItemServing by id
    @Query("SELECT * FROM ItemServing WHERE id = :id")
    suspend fun getItemServingById(id: Int): ItemServing?

    // Get all ItemServings by itemId
    @Query("SELECT * FROM ItemServing WHERE itemId = :itemId")
    suspend fun getItemServingByItemId(itemId: Int): List<ItemServing>

    // Get all ItemServings by servingId
    @Query("SELECT * FROM ItemServing WHERE servingId = :servingId")
    suspend fun getItemServingByServingId(servingId: Int): List<ItemServing>

    // Get all ItemServings by itemId and servingId
    @Query("SELECT * FROM ItemServing WHERE itemId = :itemId AND servingId = :servingId")
    suspend fun getItemServingByItemIdAndServingId(itemId: Int, servingId: Int): List<ItemServing>

    // Delete ItemServing by id
    @Query("DELETE FROM ItemServing WHERE id = :id")
    suspend fun deleteItemServingById(id: Int): Int

    // Delete ItemServing by itemId
    @Query("DELETE FROM ItemServing WHERE itemId = :itemId")
    suspend fun deleteItemServingByItemId(itemId: Int): Int

    // Delete ItemServing by servingId
    @Query("DELETE FROM ItemServing WHERE servingId = :servingId")
    suspend fun deleteItemServingByServingId(servingId: Int): Int

    // Delete ItemServing by itemId and servingId
    @Query("DELETE FROM ItemServing WHERE itemId = :itemId AND servingId = :servingId")
    suspend fun deleteItemServingByItemIdAndServingId(itemId: Int, servingId: Int): Int

    // Update ItemServing by id
    @Query("UPDATE ItemServing SET itemId = :itemId, servingId = :servingId, baseServingMultiplier = :baseServingMultiplier WHERE id = :id")
    suspend fun updateItemServingById(
        id: Int,
        itemId: Int,
        servingId: Int,
        baseServingMultiplier: Float
    ): Int

    // Update ItemServing by itemId and servingId
    @Query("UPDATE ItemServing SET baseServingMultiplier = :baseServingMultiplier WHERE itemId = :itemId AND servingId = :servingId")
    suspend fun updateItemServingByItemIdAndServingId(
        itemId: Int,
        servingId: Int,
        baseServingMultiplier: Float
    ): Int
}

@Dao
interface ItemWithServingsDao {
    // Get Item with all its Servings
    @Transaction
    @Query("SELECT * FROM Item WHERE id = :itemId")
    suspend fun getItemWithServings(itemId: Int): ItemWithServings
}