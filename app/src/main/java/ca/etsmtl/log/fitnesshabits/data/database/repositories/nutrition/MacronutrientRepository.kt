package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMacronutrientsDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMacronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithMacronutrients
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Macronutrient
import javax.inject.Inject

class MacronutrientRepository @Inject constructor(private val macronutrientDao: MacronutrientDao) {
    // Insert a macronutrient
    suspend fun insertMacronutrient(macronutrient: Macronutrient): Long {
        return macronutrientDao.insertMacronutrient(macronutrient)
    }

    // Insert multiple macronutrients
    suspend fun insertAllMacronutrients(macronutrients: List<Macronutrient>): List<Long> {
        return macronutrientDao.insertAllMacronutrients(macronutrients)
    }

    // Get macronutrient by id
    suspend fun getMacronutrientById(id: Int): Macronutrient? {
        return macronutrientDao.getMacronutrientById(id)
    }

    // Get all macronutrients by name
    suspend fun getMacronutrientByName(name: String): List<Macronutrient> {
        return macronutrientDao.getMacronutrientByName(name)
    }

    // Get all macronutrients by unitId
    suspend fun getMacronutrientByUnitId(unitId: Int): List<Macronutrient> {
        return macronutrientDao.getMacronutrientByUnitId(unitId)
    }

    // Get all macronutrients
    suspend fun getAllMacronutrients(): List<Macronutrient> {
        return macronutrientDao.getAllMacronutrients()
    }

    // Delete a macronutrient by id
    suspend fun deleteMacronutrientById(id: Int): Int {
        return macronutrientDao.deleteMacronutrientById(id)
    }

    // Delete a macronutrient by name
    suspend fun deleteMacronutrientByName(name: String): Int {
        return macronutrientDao.deleteMacronutrientByName(name)
    }

    // Update a macronutrient by id
    suspend fun updateMacronutrientById(
        id: Int,
        name: String,
        unitId: Int
    ): Int {
        return macronutrientDao.updateMacronutrientById(id, name, unitId)
    }
}

class ItemMacronutrientRepository @Inject constructor(private val itemMacronutrientDao: ItemMacronutrientDao) {
    // Insert a new ItemMacronutrient
    suspend fun insertItemMacronutrient(itemMacronutrient: ItemMacronutrient): Long {
        return itemMacronutrientDao.insertItemMacronutrient(itemMacronutrient)
    }

    // Insert multiple ItemMacronutrients
    suspend fun insertAllItemMacronutrients(itemMacronutrients: List<ItemMacronutrient>): List<Long> {
        return itemMacronutrientDao.insertAllItemMacronutrients(itemMacronutrients)
    }

    // Get ItemMacronutrient by id
    suspend fun getItemMacronutrientById(id: Int): ItemMacronutrient? {
        return itemMacronutrientDao.getItemMacronutrientById(id)
    }

    // Get all ItemMacronutrients by itemId
    suspend fun getItemMacronutrientByItemId(itemId: Int): List<ItemMacronutrient> {
        return itemMacronutrientDao.getItemMacronutrientByItemId(itemId)
    }

    // Get all ItemMacronutrients by macronutrientId
    suspend fun getItemMacronutrientByMacronutrientId(macronutrientId: Int): List<ItemMacronutrient> {
        return itemMacronutrientDao.getItemMacronutrientByMacronutrientId(macronutrientId)
    }

    // Get all ItemMacronutrients by itemId and macronutrientId
    suspend fun getItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int
    ): List<ItemMacronutrient> {
        return itemMacronutrientDao.getItemMacronutrientByItemIdAndMacronutrientId(
            itemId,
            macronutrientId
        )
    }

    // Delete ItemMacronutrient by id
    suspend fun deleteItemMacronutrientById(id: Int): Int {
        return itemMacronutrientDao.deleteItemMacronutrientById(id)
    }

    // Delete ItemMacronutrient by itemId
    suspend fun deleteItemMacronutrientByItemId(itemId: Int): Int {
        return itemMacronutrientDao.deleteItemMacronutrientByItemId(itemId)
    }

    // Delete ItemMacronutrient by macronutrientId
    suspend fun deleteItemMacronutrientByMacronutrientId(macronutrientId: Int): Int {
        return itemMacronutrientDao.deleteItemMacronutrientByMacronutrientId(macronutrientId)
    }

    // Delete ItemMacronutrient by itemId and macronutrientId
    suspend fun deleteItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int
    ): Int {
        return itemMacronutrientDao.deleteItemMacronutrientByItemIdAndMacronutrientId(
            itemId,
            macronutrientId
        )
    }

    // Update ItemMacronutrient by id
    suspend fun updateItemMacronutrientById(
        id: Int,
        itemId: Int,
        macronutrientId: Int,
        amount: Float
    ): Int {
        return itemMacronutrientDao.updateItemMacronutrientById(id, itemId, macronutrientId, amount)
    }

    // Update ItemMacronutrient by itemId and macronutrientId
    suspend fun updateItemMacronutrientByItemIdAndMacronutrientId(
        itemId: Int,
        macronutrientId: Int,
        amount: Float
    ): Int {
        return itemMacronutrientDao.updateItemMacronutrientByItemIdAndMacronutrientId(
            itemId,
            macronutrientId,
            amount
        )
    }
}

class ItemWithMacronutrientsRepository @Inject constructor(private val itemWithMacronutrientsDao: ItemWithMacronutrientsDao) {
    suspend fun getItemWithMacronutrients(itemId: Int): ItemWithMacronutrients {
        return itemWithMacronutrientsDao.getItemWithMacronutrients(itemId)
    }
}