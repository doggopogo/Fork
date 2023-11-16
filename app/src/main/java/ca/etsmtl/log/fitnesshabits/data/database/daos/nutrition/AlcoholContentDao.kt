package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.AlcoholContent

@Dao
interface AlcoholContentDao {
    // Insert a single alcohol content entry
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlcoholContent(alcoholContent: AlcoholContent): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple alcohol content entries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAlcoholContents(alcoholContents: List<AlcoholContent>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get an alcohol content entry by id
    @Query("SELECT * FROM AlcoholContent WHERE id = :id")
    suspend fun getAlcoholContentById(id: Int): AlcoholContent?

    // Get all alcohol content entries by percentage
    @Query("SELECT * FROM AlcoholContent WHERE percentage = :percentage")
    suspend fun getAlcoholContentByPercentage(percentage: Float): List<AlcoholContent>

    // Get all alcohol content entries
    @Query("SELECT * FROM AlcoholContent")
    suspend fun getAllAlcoholContents(): List<AlcoholContent>

    // Delete an alcohol content entry by id
    @Query("DELETE FROM AlcoholContent WHERE id = :id")
    suspend fun deleteAlcoholContentById(id: Int): Int    // by convention, deleting a row returns the number of affected rows as an Int

    // Update an alcohol content entry by id
    @Query("UPDATE AlcoholContent SET percentage = :percentage WHERE id = :id")
    suspend fun updateAlcoholContentById(id: Int, percentage: Float): Int  // by convention, updating a row returns the number of affected rows as an Int
}
