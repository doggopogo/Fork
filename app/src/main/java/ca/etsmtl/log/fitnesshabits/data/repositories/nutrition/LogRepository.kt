package ca.etsmtl.log.fitnesshabits.data.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.LogDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import javax.inject.Inject

class LogRepository @Inject constructor(private val logDao: LogDao) {
    // Insert a log
    suspend fun insertLog(log: Log): Long {
        return logDao.insertLog(log)
    }

    // Insert multiple logs
    suspend fun insertAllLogs(logs: List<Log>): List<Long> {
        return logDao.insertAllLogs(logs)
    }

    // Get all log by id
    suspend fun getLogById(id: Int): Log? {
        return logDao.getLogById(id)
    }

    // Get all log by itemId
    suspend fun getLogByItemId(itemId: Int): Log? {
        return logDao.getLogByItemId(itemId)
    }

    // Get all log by timestamp
    suspend fun getLogByTimestamp(timestamp: Long): Log? {
        return logDao.getLogByTimestamp(timestamp)
    }

    // Get all logs
    suspend fun getAllLogs(): List<Log> {
        return logDao.getAllLogs()
    }

    // Delete a log by id
    suspend fun deleteLogById(id: Int): Int {
        return logDao.deleteLogById(id)
    }

    // Update a log by id
    suspend fun updateLogById(
        id: Int,
        itemId: Int,
        timestamp: Long,
        servingMultiplier: Float
    ): Int {
        return logDao.updateLogById(id, itemId, timestamp, servingMultiplier)
    }

    // Get the logs for items of type typeId
    suspend fun getLogsByItemTypeId(typeId: NutritionType): List<Log>
    {
        return logDao.getLogsByItemTypeId(typeId.ordinal)
    }

    // Get item id list of most commonly used item of type id
    suspend fun getItemIdsOrderedByUsage(typeId: NutritionType): List<Int>
    {
        return logDao.getItemIdsOrderedByUsage(typeId.ordinal)
    }
}
