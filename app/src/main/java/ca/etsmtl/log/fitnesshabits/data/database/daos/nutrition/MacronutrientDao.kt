package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMacronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithMacronutrients
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Macronutrient

@Dao
interface MacronutrientDao {
    // Insert a single macronutrient
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMacronutrient(macronutrient: Macronutrient): Long

    // Insert multiple macronutrients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMacronutrients(macronutrients: List<Macronutrient>): List<Long>

    // Get macronutrient by id
    @Query("SELECT * FROM Macronutrient WHERE id = :id")
    suspend fun getMacronutrientById(id: Int): Macronutrient?

    // Get all macronutrients by name
    @Query("SELECT * FROM Macronutrient WHERE name = :name")
    suspend fun getMacronutrientByName(name: String): List<Macronutrient>

    // Get all macronutrients by unitId
    @Query("SELECT * FROM Macronutrient WHERE unitId = :unitId")
    suspend fun getMacronutrientByUnitId(unitId: Int): List<Macronutrient>

    // Get all macronutrients
    @Query("SELECT * FROM Macronutrient")
    suspend fun getAllMacronutrients(): List<Macronutrient>

    // Delete macronutrient by id
    @Query("DELETE FROM Macronutrient WHERE id = :id")
    suspend fun deleteMacronutrientById(id: Int): Int

    // Delete macronutrient by name
    @Query("DELETE FROM Macronutrient WHERE name = :name")
    suspend fun deleteMacronutrientByName(name: String): Int

    // Update a macronutrient by id
    @Query("UPDATE Macronutrient SET name = :name, unitId = :unitId WHERE id = :id")
    suspend fun updateMacronutrientById(
        id: Int,
        name: String,
        unitId: Int
    ): Int
}

@Dao
interface ItemMacronutrientDao {
    // Insert a new ItemMacronutrient
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemMacronutrient(itemMacronutrient: ItemMacronutrient): Long

    // Insert multiple ItemMacronutrients
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemMacronutrients(itemMacronutrients: List<ItemMacronutrient>): List<Long>

    // Get ItemMacronutrient by id
    @Query("SELECT * FROM ItemMacronutrient WHERE id = :id")
    suspend fun getItemMacronutrientById(id: Int): ItemMacronutrient?

    // Get all ItemMacronutrients by itemId
    @Query("SELECT * FROM ItemMacronutrient WHERE itemId = :itemId")
    suspend fun getItemMacronutrientByItemId(itemId: Int): List<ItemMacronutrient>

    // Get all ItemMacronutrients by macronutrientId
    @Query("SELECT * FROM ItemMacronutrient WHERE macronutrientId = :macronutrientId")
    suspend fun getItemMacronutrientByMacronutrientId(macronutrientId: Int): List<ItemMacronutrient>

    // Get all ItemMacronutrients by itemId and macronutrientId
    @Query("SELECT * FROM ItemMacronutrient WHERE itemId = :itemId AND macronutrientId = :macronutrientId")
    suspend fun getItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int
    ): List<ItemMacronutrient>

    // Delete ItemMacronutrient by id
    @Query("DELETE FROM ItemMacronutrient WHERE id = :id")
    suspend fun deleteItemMacronutrientById(id: Int): Int

    // Delete ItemMacronutrient by itemId
    @Query("DELETE FROM ItemMacronutrient WHERE itemId = :itemId")
    suspend fun deleteItemMacronutrientByItemId(itemId: Int): Int

    // Delete ItemMacronutrient by macronutrientId
    @Query("DELETE FROM ItemMacronutrient WHERE macronutrientId = :macronutrientId")
    suspend fun deleteItemMacronutrientByMacronutrientId(macronutrientId: Int): Int

    // Delete ItemMacronutrient by itemId and macronutrientId
    @Query("DELETE FROM ItemMacronutrient WHERE itemId = :itemId AND macronutrientId = :macronutrientId")
    suspend fun deleteItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int
    ): Int

    // Update ItemMacronutrient by id
    @Query("UPDATE ItemMacronutrient SET itemId = :itemId, macronutrientId = :macronutrientId, amount = :amount WHERE id = :id")
    suspend fun updateItemMacronutrientById(
        id: Int,
        itemId: Int,
        macronutrientId: Int,
        amount: Int
    ): Int

    // Update ItemMacronutrient by itemId and macronutrientId
    @Query("UPDATE ItemMacronutrient SET amount = :amount WHERE itemId = :itemId AND macronutrientId = :macronutrientId")
    suspend fun updateItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int,
        amount: Int
    ): Int
}

@Dao
interface ItemWithMacronutrientsDao {
    // Get Item with all its Macronutrients
    @Transaction
    @Query("SELECT * FROM Item WHERE id = :itemId")
    suspend fun getItemWithMacronutrients(itemId: Int): ItemWithMacronutrients
}