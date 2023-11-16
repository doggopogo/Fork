package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.HydrationIndexDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.HydrationIndex
import javax.inject.Inject

class HydrationIndexRepository @Inject constructor(private val hydrationIndexDao: HydrationIndexDao) {
    // Insert a hydration index
    suspend fun insertHydrationIndex(hydrationIndex: HydrationIndex): Long {
        return hydrationIndexDao.insertHydrationIndex(hydrationIndex)
    }

    // Insert multiple hydration indexes
    suspend fun insertAllHydrationIndexes(hydrationIndexes: List<HydrationIndex>): List<Long> {
        return hydrationIndexDao.insertAllHydrationIndexes(hydrationIndexes)
    }

    // Get a hydration index by id
    suspend fun getHydrationIndexById(id: Int): HydrationIndex? {
        return hydrationIndexDao.getHydrationIndexById(id)
    }

    // Get all hydration index by description
    suspend fun getHydrationIndexByDescription(description: String): List<HydrationIndex> {
        return hydrationIndexDao.getHydrationIndexByDescription(description)
    }

    // Get all hydration index by multiplier
    suspend fun getHydrationIndexByMultiplier(multiplier: Float): List<HydrationIndex> {
        return hydrationIndexDao.getHydrationIndexByMultiplier(multiplier)
    }

    // Get all hydration index
    suspend fun getAllHydrationIndexes(): List<HydrationIndex> {
        return hydrationIndexDao.getAllHydrationIndexes()
    }

    // Delete a hydration index by id
    suspend fun deleteHydrationIndexById(id: Int): Int {
        return hydrationIndexDao.deleteHydrationIndexById(id)
    }

    // Update a hydration index by id
    suspend fun updateHydrationIndexById(id: Int, description: String, multiplier: Float): Int {
        return hydrationIndexDao.updateHydrationIndexById(id, description, multiplier)
    }
}
