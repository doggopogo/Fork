package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMicronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithMicronutrients
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Micronutrient

@Dao
interface MicronutrientDao {
    // Insert a single micronutrient
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMicronutrient(micronutrient: Micronutrient): Long

    // Insert multiple micronutrients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMicronutrients(micronutrients: List<Micronutrient>): List<Long>

    // Get micronutrient by id
    @Query("SELECT * FROM Micronutrient WHERE id = :id")
    suspend fun getMicronutrientById(id: Int): Micronutrient?

    // Get all micronutrients by name
    @Query("SELECT * FROM Micronutrient WHERE name = :name")
    suspend fun getMicronutrientByName(name: String): List<Micronutrient>

    // Get all micronutrients by unitId
    @Query("SELECT * FROM Micronutrient WHERE unitId = :unitId")
    suspend fun getMicronutrientByUnitId(unitId: Int): List<Micronutrient>

    // Get all micronutrients
    @Query("SELECT * FROM Micronutrient")
    suspend fun getAllMicronutrients(): List<Micronutrient>

    // Delete micronutrient by id
    @Query("DELETE FROM Micronutrient WHERE id = :id")
    suspend fun deleteMicronutrientById(id: Int): Int

    // Delete micronutrient by name
    @Query("DELETE FROM Micronutrient WHERE name = :name")
    suspend fun deleteMicronutrientByName(name: String): Int

    // Update a micronutrient by id
    @Query("UPDATE Micronutrient SET name = :name, unitId = :unitId WHERE id = :id")
    suspend fun updateMicronutrientById(
        id: Int,
        name: String,
        unitId: Int
    ): Int
}

@Dao
interface ItemMicronutrientDao {
    // Insert a new ItemMicronutrient
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemMicronutrient(itemMicronutrient: ItemMicronutrient): Long

    // Insert multiple ItemMicronutrients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemMicronutrients(itemMicronutrients: List<ItemMicronutrient>): List<Long>

    // Get ItemMicronutrient by id
    @Query("SELECT * FROM ItemMicronutrient WHERE id = :id")
    suspend fun getItemMicronutrientById(id: Int): ItemMicronutrient?

    // Get all ItemMicronutrients by itemId
    @Query("SELECT * FROM ItemMicronutrient WHERE itemId = :itemId")
    suspend fun getItemMicronutrientByItemId(itemId: Int): List<ItemMicronutrient>

    // Get all ItemMicronutrients by micronutrientId
    @Query("SELECT * FROM ItemMicronutrient WHERE micronutrientId = :micronutrientId")
    suspend fun getItemMicronutrientByMicronutrientId(micronutrientId: Int): List<ItemMicronutrient>

    // Get all ItemMicronutrients by itemId and micronutrientId
    @Query("SELECT * FROM ItemMicronutrient WHERE itemId = :itemId AND micronutrientId = :micronutrientId")
    suspend fun getItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int
    ): List<ItemMicronutrient>

    // Delete ItemMicronutrient by id
    @Query("DELETE FROM ItemMicronutrient WHERE id = :id")
    suspend fun deleteItemMicronutrientById(id: Int): Int

    // Delete ItemMicronutrient by itemId
    @Query("DELETE FROM ItemMicronutrient WHERE itemId = :itemId")
    suspend fun deleteItemMicronutrientByItemId(itemId: Int): Int

    // Delete ItemMicronutrient by micronutrientId
    @Query("DELETE FROM ItemMicronutrient WHERE micronutrientId = :micronutrientId")
    suspend fun deleteItemMicronutrientByMicronutrientId(micronutrientId: Int): Int

    // Delete ItemMicronutrient by itemId and micronutrientId
    @Query("DELETE FROM ItemMicronutrient WHERE itemId = :itemId AND micronutrientId = :micronutrientId")
    suspend fun deleteItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int
    ): Int

    // Update ItemMicronutrient by id
    @Query("UPDATE ItemMicronutrient SET itemId = :itemId, micronutrientId = :micronutrientId, amount = :amount WHERE id = :id")
    suspend fun updateItemMicronutrientById(
        id: Int,
        itemId: Int,
        micronutrientId: Int,
        amount: Int
    ): Int

    // Update ItemMicronutrient by itemId and micronutrientId
    @Query("UPDATE ItemMicronutrient SET amount = :amount WHERE itemId = :itemId AND micronutrientId = :micronutrientId")
    suspend fun updateItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int,
        amount: Int
    ): Int
}

@Dao
interface ItemWithMicronutrientsDao {
    // Get Item with all its Micronutrients
    @Transaction
    @Query("SELECT * FROM Item WHERE id = :itemId")
    suspend fun getItemWithMicronutrients(itemId: Int): ItemWithMicronutrients
}