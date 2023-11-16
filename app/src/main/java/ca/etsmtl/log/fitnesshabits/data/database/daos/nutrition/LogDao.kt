package ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log

@Dao
interface LogDao {
    // Insert a single log
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: Log): Long    // by convention, Insert methods return id as a Long when successfully inserted

    // Insert multiple logs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLogs(logs: List<Log>): List<Long>    // by convention, InsertAll method return a list of all the successfully inserted id as Long

    // Get all logs by id
    @Query("SELECT * FROM Log WHERE id = :id")
    suspend fun getLogById(id: Int): Log?

    // Get all logs by itemId
    @Query("SELECT * FROM Log WHERE itemId = :itemId")
    suspend fun getLogByItemId(itemId: Int): Log?

    // Get all logs by timestamp
    @Query("SELECT * FROM Log WHERE timestamp = :timestamp")
    suspend fun getLogByTimestamp(timestamp: Long): Log?

    // Get all logs
    @Query("SELECT * FROM Log")
    suspend fun getAllLogs(): List<Log>

    // Delete a log by ID
    @Query("DELETE FROM Log WHERE id = :logId")
    suspend fun deleteLogById(logId: Int): Int  // by convention, deleting a row returns the number of affected rows as an Int

    // Update a log by ID
    @Query("UPDATE Log SET itemId = :itemId, timestamp = :timestamp, servingMultiplier = :servingMultiplier WHERE id = :id")
    suspend fun updateLogById(
        id: Int,
        itemId: Int,
        timestamp: Long,
        servingMultiplier: Float
    ): Int  // by convention, updating a row returns the number of affected rows as an Int
}