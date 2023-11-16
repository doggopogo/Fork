package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.HydrationIndex

@Dao
interface HydrationIndexDao {
    // Insert a single hydration index
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHydrationIndex(hydrationIndex: HydrationIndex): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple hydration indices
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHydrationIndexes(hydrationIndices: List<HydrationIndex>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get a hydration index by id
    @Query("SELECT * FROM HydrationIndex WHERE id = :id")
    suspend fun getHydrationIndexById(id: Int): HydrationIndex?

    // Get all hydration index by description
    @Query("SELECT * FROM HydrationIndex WHERE description LIKE :description")
    suspend fun getHydrationIndexByDescription(description: String): List<HydrationIndex>

    // Get all hydration index by multiplier
    @Query("SELECT * FROM HydrationIndex WHERE multiplier = :multiplier")
    suspend fun getHydrationIndexByMultiplier(multiplier: Float): List<HydrationIndex>

    // Get all hydration index
    @Query("SELECT * FROM HydrationIndex")
    suspend fun getAllHydrationIndexes(): List<HydrationIndex>

    // Delete a hydration index by id
    @Query("DELETE FROM HydrationIndex WHERE id = :id")
    suspend fun deleteHydrationIndexById(id: Int): Int    // by convention, deleting a row returns the number of affected rows as an Int

    // Update a hydration index by id
    @Query("UPDATE HydrationIndex SET description = :description, multiplier = :multiplier WHERE id = :id")
    suspend fun updateHydrationIndexById(id: Int, description: String, multiplier: Float): Int    // by convention, updating a row returns the number of affected rows as an Int
}
