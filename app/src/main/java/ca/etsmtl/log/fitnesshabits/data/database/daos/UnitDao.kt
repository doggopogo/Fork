package ca.etsmtl.log.fitnesshabits.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.Unit

@Dao
interface UnitDao {
    // Insert a single unit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple units
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUnits(units: List<Unit>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get a unit by id
    @Query("SELECT * FROM Unit WHERE id = :id")
    suspend fun getUnitById(id: Int): Unit?

    // Get all units by name
    @Query("SELECT * FROM Unit WHERE name LIKE :name")
    suspend fun getUnitByName(name: String): List<Unit>

    // Get all units
    @Query("SELECT * FROM Unit")
    suspend fun getAllUnits(): List<Unit>

    // Delete a unit by id
    @Query("DELETE FROM Unit WHERE id = :id")
    suspend fun deleteUnitById(id: Int): Int    // by convention, deleting a row returns the number of affected rows as an Int

    // Update a unit by id
    @Query("UPDATE Unit SET name = :name WHERE id = :id")
    suspend fun updateUnitById(id: Int, name: String): Int  // by convention, updating a row returns the number of affected rows as an Int
}