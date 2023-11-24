package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemBioactiveCompound
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithBioactiveCompounds
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.BioactiveCompound

@Dao
interface BioactiveCompoundDao {
    // Insert a single BioactiveCompound
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBioactiveCompound(bioactiveCompound: BioactiveCompound): Long

    // Insert multiple BioactiveCompounds
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBioactiveCompounds(bioactiveCompounds: List<BioactiveCompound>): List<Long>

    // Get BioactiveCompound by id
    @Query("SELECT * FROM BioactiveCompound WHERE id = :id")
    suspend fun getBioactiveCompoundById(id: Int): BioactiveCompound?

    // Get all BioactiveCompounds by name
    @Query("SELECT * FROM BioactiveCompound WHERE name = :name")
    suspend fun getBioactiveCompoundByName(name: String): List<BioactiveCompound>

    // Get all BioactiveCompounds by unitId
    @Query("SELECT * FROM BioactiveCompound WHERE unitId = :unitId")
    suspend fun getBioactiveCompoundByUnitId(unitId: Int): List<BioactiveCompound>

    // Get all BioactiveCompounds
    @Query("SELECT * FROM BioactiveCompound")
    suspend fun getAllBioactiveCompounds(): List<BioactiveCompound>

    // Delete BioactiveCompound by id
    @Query("DELETE FROM BioactiveCompound WHERE id = :id")
    suspend fun deleteBioactiveCompoundById(id: Int): Int

    // Delete BioactiveCompound by name
    @Query("DELETE FROM BioactiveCompound WHERE name = :name")
    suspend fun deleteBioactiveCompoundByName(name: String): Int

    // Update a BioactiveCompound by id
    @Query("UPDATE BioactiveCompound SET name = :name, unitId = :unitId WHERE id = :id")
    suspend fun updateBioactiveCompoundById(
        id: Int,
        name: String,
        unitId: Int
    ): Int
}

@Dao
interface ItemBioactiveCompoundDao {
    // Insert a new ItemBioactiveCompound
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemBioactiveCompound(itemBioactiveCompound: ItemBioactiveCompound): Long

    // Insert multiple ItemBioactiveCompounds
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItemBioactiveCompounds(itemBioactiveCompounds: List<ItemBioactiveCompound>): List<Long>

    // Get ItemBioactiveCompound by id
    @Query("SELECT * FROM ItemBioactiveCompound WHERE id = :id")
    suspend fun getItemBioactiveCompoundById(id: Int): ItemBioactiveCompound?

    // Get all ItemBioactiveCompounds by itemId
    @Query("SELECT * FROM ItemBioactiveCompound WHERE itemId = :itemId")
    suspend fun getItemBioactiveCompoundByItemId(itemId: Int): List<ItemBioactiveCompound>

    // Get all ItemBioactiveCompounds by BioactiveCompoundId
    @Query("SELECT * FROM ItemBioactiveCompound WHERE bioactiveCompoundId = :bioactiveCompoundId")
    suspend fun getItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId: Int): List<ItemBioactiveCompound>

    // Get all ItemBioactiveCompounds by itemId and BioactiveCompoundId
    @Query("SELECT * FROM ItemBioactiveCompound WHERE itemId = :itemId AND bioactiveCompoundId = :bioactiveCompoundId")
    suspend fun getItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int
    ): List<ItemBioactiveCompound>

    // Delete ItemBioactiveCompound by id
    @Query("DELETE FROM ItemBioactiveCompound WHERE id = :id")
    suspend fun deleteItemBioactiveCompoundById(id: Int): Int

    // Delete ItemBioactiveCompound by itemId
    @Query("DELETE FROM ItemBioactiveCompound WHERE itemId = :itemId")
    suspend fun deleteItemBioactiveCompoundByItemId(itemId: Int): Int

    // Delete ItemBioactiveCompound by BioactiveCompoundId
    @Query("DELETE FROM ItemBioactiveCompound WHERE bioactiveCompoundId = :bioactiveCompoundId")
    suspend fun deleteItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId: Int): Int

    // Delete ItemBioactiveCompound by itemId and BioactiveCompoundId
    @Query("DELETE FROM ItemBioactiveCompound WHERE itemId = :itemId AND bioactiveCompoundId = :bioactiveCompoundId")
    suspend fun deleteItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int
    ): Int

    // Update ItemBioactiveCompound by id
    @Query("UPDATE ItemBioactiveCompound SET itemId = :itemId, bioactiveCompoundId = :bioactiveCompoundId, amount = :amount WHERE id = :id")
    suspend fun updateItemBioactiveCompoundById(
        id: Int,
        itemId: Int,
        bioactiveCompoundId: Int,
        amount: Int
    ): Int

    // Update ItemBioactiveCompound by itemId and BioactiveCompoundId
    @Query("UPDATE ItemBioactiveCompound SET amount = :amount WHERE itemId = :itemId AND bioactiveCompoundId = :bioactiveCompoundId")
    suspend fun updateItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int,
        amount: Int
    ): Int
}

@Dao
interface ItemWithBioactiveCompoundsDao {
    // Get Item with all its BioactiveCompounds
    @Transaction
    @Query("SELECT * FROM Item WHERE id = :itemId")
    suspend fun getItemWithBioactiveCompounds(itemId: Int): ItemWithBioactiveCompounds
}