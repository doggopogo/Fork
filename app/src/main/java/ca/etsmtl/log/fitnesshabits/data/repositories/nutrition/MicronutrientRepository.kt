package ca.etsmtl.log.fitnesshabits.data.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMicronutrientsDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMicronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithMicronutrients
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Micronutrient
import javax.inject.Inject

class MicronutrientRepository @Inject constructor(private val micronutrientDao: MicronutrientDao) {
    // Insert a micronutrient
    suspend fun insertMicronutrient(micronutrient: Micronutrient): Long {
        return micronutrientDao.insertMicronutrient(micronutrient)
    }

    // Insert multiple micronutrients
    suspend fun insertAllMicronutrients(micronutrients: List<Micronutrient>): List<Long> {
        return micronutrientDao.insertAllMicronutrients(micronutrients)
    }

    // Get micronutrient by id
    suspend fun getMicronutrientById(id: Int): Micronutrient? {
        return micronutrientDao.getMicronutrientById(id)
    }

    // Get all micronutrients by name
    suspend fun getMicronutrientByName(name: String): List<Micronutrient> {
        return micronutrientDao.getMicronutrientByName(name)
    }

    // Get all micronutrients by unitId
    suspend fun getMicronutrientByUnitId(unitId: Int): List<Micronutrient> {
        return micronutrientDao.getMicronutrientByUnitId(unitId)
    }

    // Get all micronutrients
    suspend fun getAllMicronutrients(): List<Micronutrient> {
        return micronutrientDao.getAllMicronutrients()
    }

    // Delete a micronutrient by id
    suspend fun deleteMicronutrientById(id: Int): Int {
        return micronutrientDao.deleteMicronutrientById(id)
    }

    // Delete a micronutrient by name
    suspend fun deleteMicronutrientByName(name: String): Int {
        return micronutrientDao.deleteMicronutrientByName(name)
    }

    // Update a micronutrient by id
    suspend fun updateMicronutrientById(
        id: Int,
        name: String,
        unitId: Int
    ): Int {
        return micronutrientDao.updateMicronutrientById(id, name, unitId)
    }
}

class ItemMicronutrientRepository @Inject constructor(private val itemMicronutrientDao: ItemMicronutrientDao) {
    // Insert a new ItemMicronutrient
    suspend fun insertItemMicronutrient(itemMicronutrient: ItemMicronutrient): Long {
        return itemMicronutrientDao.insertItemMicronutrient(itemMicronutrient)
    }

    // Insert multiple ItemMicronutrients
    suspend fun insertAllItemMicronutrients(itemMicronutrients: List<ItemMicronutrient>): List<Long> {
        return itemMicronutrientDao.insertAllItemMicronutrients(itemMicronutrients)
    }

    // Get ItemMicronutrient by id
    suspend fun getItemMicronutrientById(id: Int): ItemMicronutrient? {
        return itemMicronutrientDao.getItemMicronutrientById(id)
    }

    // Get all ItemMicronutrients by itemId
    suspend fun getItemMicronutrientByItemId(itemId: Int): List<ItemMicronutrient> {
        return itemMicronutrientDao.getItemMicronutrientByItemId(itemId)
    }

    // Get all ItemMicronutrients by micronutrientId
    suspend fun getItemMicronutrientByMicronutrientId(micronutrientId: Int): List<ItemMicronutrient> {
        return itemMicronutrientDao.getItemMicronutrientByMicronutrientId(micronutrientId)
    }

    // Get all ItemMicronutrients by itemId and micronutrientId
    suspend fun getItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int
    ): List<ItemMicronutrient> {
        return itemMicronutrientDao.getItemMicronutrientByItemIdAndMicronutrientId(
            itemId,
            micronutrientId
        )
    }

    // Delete ItemMicronutrient by id
    suspend fun deleteItemMicronutrientById(id: Int): Int {
        return itemMicronutrientDao.deleteItemMicronutrientById(id)
    }

    // Delete ItemMicronutrient by itemId
    suspend fun deleteItemMicronutrientByItemId(itemId: Int): Int {
        return itemMicronutrientDao.deleteItemMicronutrientByItemId(itemId)
    }

    // Delete ItemMicronutrient by micronutrientId
    suspend fun deleteItemMicronutrientByMicronutrientId(micronutrientId: Int): Int {
        return itemMicronutrientDao.deleteItemMicronutrientByMicronutrientId(micronutrientId)
    }

    // Delete ItemMicronutrient by itemId and micronutrientId
    suspend fun deleteItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int
    ): Int {
        return itemMicronutrientDao.deleteItemMicronutrientByItemIdAndMicronutrientId(
            itemId,
            micronutrientId
        )
    }

    // Update ItemMicronutrient by id
    suspend fun updateItemMicronutrientById(
        id: Int,
        itemId: Int,
        micronutrientId: Int,
        amount: Int
    ): Int {
        return itemMicronutrientDao.updateItemMicronutrientById(id, itemId, micronutrientId, amount)
    }

    // Update ItemMicronutrient by itemId and micronutrientId
    suspend fun updateItemMicronutrientByItemIdAndMicronutrientId(
        itemId: Int,
        micronutrientId: Int,
        amount: Int
    ): Int {
        return itemMicronutrientDao.updateItemMicronutrientByItemIdAndMicronutrientId(
            itemId,
            micronutrientId,
            amount
        )
    }
}

class ItemWithMicronutrientsRepository @Inject constructor(private val itemWithMicronutrientsDao: ItemWithMicronutrientsDao) {
    suspend fun getItemWithMicronutrients(itemId: Int): ItemWithMicronutrients {
        return itemWithMicronutrientsDao.getItemWithMicronutrients(itemId)
    }
}