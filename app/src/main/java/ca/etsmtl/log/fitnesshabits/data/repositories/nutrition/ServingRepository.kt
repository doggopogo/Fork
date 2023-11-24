package ca.etsmtl.log.fitnesshabits.data.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithServingsDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ServingDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemServing
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithServings
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving
import javax.inject.Inject

class ServingRepository @Inject constructor(private val servingDao: ServingDao) {
    // Insert a serving
    suspend fun insertServing(serving: Serving): Long {
        return servingDao.insertServing(serving)
    }

    // Insert multiple servings
    suspend fun insertAllServings(servings: List<Serving>): List<Long> {
        return servingDao.insertAllServings(servings)
    }

    // Get serving by id
    suspend fun getServingById(id: Int): Serving? {
        return servingDao.getServingById(id)
    }

    // Get all servings by name
    suspend fun getServingByName(name: String): List<Serving> {
        return servingDao.getServingByName(name)
    }

    // Get all servings by unitId
    suspend fun getServingByUnitId(unitId: Int): List<Serving> {
        return servingDao.getServingByUnitId(unitId)
    }

    // Get all servings
    suspend fun getAllServings(): List<Serving> {
        return servingDao.getAllServings()
    }

    // Delete a serving by id
    suspend fun deleteServingById(id: Int): Int {
        return servingDao.deleteServingById(id)
    }

    // Update a serving by id
    suspend fun updateServingById(
        id: Int,
        size: Int,
        name: String,
        amount: Int,
        unitId: Int
    ): Int {
        return servingDao.updateServingById(id, size, name, amount, unitId)
    }
}

class ItemServingRepository @Inject constructor(private val itemServingDao: ItemServingDao) {
    // Insert a new ItemServing
    suspend fun insertItemServing(itemServing: ItemServing): Long {
        return itemServingDao.insertItemServing(itemServing)
    }

    // Insert multiple ItemServings
    suspend fun insertAllItemServings(itemServings: List<ItemServing>): List<Long> {
        return itemServingDao.insertAllItemServings(itemServings)
    }

    // Get ItemServing by id
    suspend fun getItemServingById(id: Int): ItemServing? {
        return itemServingDao.getItemServingById(id)
    }

    // Get all ItemServings by itemId
    suspend fun getItemServingByItemId(itemId: Int): List<ItemServing> {
        return itemServingDao.getItemServingByItemId(itemId)
    }

    // Get all ItemServings by servingId
    suspend fun getItemServingByServingId(servingId: Int): List<ItemServing> {
        return itemServingDao.getItemServingByServingId(servingId)
    }

    // Get all ItemServings by itemId and servingId
    suspend fun getItemServingByItemIdAndServingId(itemId: Int, servingId: Int): List<ItemServing> {
        return itemServingDao.getItemServingByItemIdAndServingId(itemId, servingId)
    }

    // Delete ItemServing by id
    suspend fun deleteItemServingById(id: Int): Int {
        return itemServingDao.deleteItemServingById(id)
    }

    // Delete ItemServing by itemId
    suspend fun deleteItemServingByItemId(itemId: Int): Int {
        return itemServingDao.deleteItemServingByItemId(itemId)
    }

    // Delete ItemServing by servingId
    suspend fun deleteItemServingByServingId(servingId: Int): Int {
        return itemServingDao.deleteItemServingByServingId(servingId)
    }

    // Delete ItemServing by itemId and servingId
    suspend fun deleteItemServingByItemIdAndServingId(itemId: Int, servingId: Int): Int {
        return itemServingDao.deleteItemServingByItemIdAndServingId(itemId, servingId)
    }

    // Update ItemServing by id
    suspend fun updateItemServingById(
        id: Int,
        itemId: Int,
        servingId: Int,
        baseServingMultiplier: Float
    ): Int {
        return itemServingDao.updateItemServingById(id, itemId, servingId, baseServingMultiplier)
    }

    // Update ItemServing by itemId and servingId
    suspend fun updateItemServingByItemIdAndServingId(
        itemId: Int,
        servingId: Int,
        baseServingMultiplier: Float
    ): Int {
        return itemServingDao.updateItemServingByItemIdAndServingId(itemId, servingId, baseServingMultiplier)
    }
}

class ItemWithServingsRepository @Inject constructor(private val itemWithServingsDao: ItemWithServingsDao) {
    suspend fun getItemWithServings(itemId: Int): ItemWithServings {
        return itemWithServingsDao.getItemWithServings(itemId)
    }
}