package ca.etsmtl.log.fitnesshabits.data.repositories

import ca.etsmtl.log.fitnesshabits.data.database.daos.UnitDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.Unit
import javax.inject.Inject

class UnitRepository @Inject constructor(private val unitDao: UnitDao) {
    // Insert a unit
    suspend fun insertUnit(unit: Unit): Long {
        return unitDao.insertUnit(unit)
    }

    // Insert multiple units
    suspend fun insertAllUnits(units: List<Unit>): List<Long> {
        return unitDao.insertAllUnits(units)
    }

    // Get a unit by id
    suspend fun getUnitById(id: Int): Unit? {
        return unitDao.getUnitById(id)
    }

    // Get all units by name
    suspend fun getUnitByName(name: String): List<Unit> {
        return unitDao.getUnitByName(name)
    }

    // Get all units
    suspend fun getAllUnits(): List<Unit> {
        return unitDao.getAllUnits()
    }

    // Delete a unit by id
    suspend fun deleteUnitById(id: Int): Int {
        return unitDao.deleteUnitById(id)
    }

    // Update a unit by id
    suspend fun updateUnitById(id: Int, name: String): Int {
        return unitDao.updateUnitById(id, name)
    }
}
