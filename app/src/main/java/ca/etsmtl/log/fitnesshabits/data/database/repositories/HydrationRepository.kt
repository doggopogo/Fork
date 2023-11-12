package ca.etsmtl.log.fitnesshabits.data.database.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import ca.etsmtl.log.fitnesshabits.data.database.daos.HydrationDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationType

class HydrationRepository(private val hydrationDao: HydrationDao) {

    // Function to insert a hydration type into the database
    suspend fun insertHydrationType(hydrationType: HydrationType) {
        hydrationDao.insertHydrationType(hydrationType)
    }

    fun getAllHydrationTypes(): LiveData<List<HydrationType>> {
        return hydrationDao.getAllHydrationTypes().asLiveData()
    }

    // If you need to perform other operations like fetching data, updating, or deleting,
    // you would define those functions here as well, using the hydrationDao.
}
