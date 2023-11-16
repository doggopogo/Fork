package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.AlcoholContentDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.AlcoholContent
import javax.inject.Inject

class AlcoholContentRepository @Inject constructor(private val alcoholContentDao: AlcoholContentDao) {
    // Insert an alcohol content
    suspend fun insertAlcoholContent(alcoholContent: AlcoholContent): Long {
        return alcoholContentDao.insertAlcoholContent(alcoholContent)
    }

    // Insert multiple alcohol contents
    suspend fun insertAllAlcoholContents(alcoholContents: List<AlcoholContent>): List<Long> {
        return alcoholContentDao.insertAllAlcoholContents(alcoholContents)
    }

    // Get an alcohol content by id
    suspend fun getAlcoholContentById(id: Int): AlcoholContent? {
        return alcoholContentDao.getAlcoholContentById(id)
    }

    // Get all alcohol contents by percentage
    suspend fun getAlcoholContentByPercentage(percentage: Float): List<AlcoholContent> {
        return alcoholContentDao.getAlcoholContentByPercentage(percentage)
    }

    // Get all alcohol contents
    suspend fun getAllAlcoholContents(): List<AlcoholContent> {
        return alcoholContentDao.getAllAlcoholContents()
    }

    // Delete an alcohol content by id
    suspend fun deleteAlcoholContentById(id: Int): Int {
        return alcoholContentDao.deleteAlcoholContentById(id)
    }

    // Update an alcohol content by id
    suspend fun updateAlcoholContentById(id: Int, percentage: Float): Int {
        return alcoholContentDao.updateAlcoholContentById(id, percentage)
    }
}
