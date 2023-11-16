package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Type

@Dao
interface TypeDao {
    // Insert a single type
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: Type): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple types
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTypes(types: List<Type>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get a type by id
    @Query("SELECT * FROM Type WHERE id = :id")
    suspend fun getTypeById(id: Int): Type?

    // Get all types by name
    @Query("SELECT * FROM Type WHERE name LIKE :name")
    suspend fun getTypeByName(name: String): List<Type>

    // Get all types
    @Query("SELECT * FROM Type")
    suspend fun getAllTypes(): List<Type>

    // Delete a type by id
    @Query("DELETE FROM Type WHERE id = :id")
    suspend fun deleteTypeById(id: Int): Int    // by convention, deleting a row returns the number of affected rows as an Int

    // Update a type by id
    @Query("UPDATE Type SET name = :name WHERE id = :id")
    suspend fun updateTypeById(id: Int, name: String): Int  // by convention, updating a row returns the number of affected rows as an Int
}