package ca.etsmtl.log.fitnesshabits.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface TaskItemDao {
    @Query("SELECT * FROM Hydration")
    fun getAll(): List<Hydration>

    @Query("SELECT * FROM Hydration WHERE id")
    fun findById(userIds: IntArray): List<Hydration>

    @Query("SELECT * FROM Hydration WHERE name")
    fun findByName(drinkName: String): Hydration

    @Insert
    fun insertAll(vararg drinks: Hydration)

    @Delete
    fun delete(drink: Hydration)
}