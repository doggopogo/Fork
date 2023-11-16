package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemBioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithBioactiveCompoundsDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.BioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemBioactiveCompound
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemWithBioactiveCompounds
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.BioactiveCompound
import javax.inject.Inject

class BioactiveCompoundRepository @Inject constructor(private val bioactiveCompoundDao: BioactiveCompoundDao) {
    // Insert a BioactiveCompound
    suspend fun insertBioactiveCompound(bioactiveCompound: BioactiveCompound): Long {
        return bioactiveCompoundDao.insertBioactiveCompound(bioactiveCompound)
    }

    // Insert multiple BioactiveCompounds
    suspend fun insertAllBioactiveCompounds(bioactiveCompounds: List<BioactiveCompound>): List<Long> {
        return bioactiveCompoundDao.insertAllBioactiveCompounds(bioactiveCompounds)
    }

    // Get BioactiveCompound by id
    suspend fun getBioactiveCompoundById(id: Int): BioactiveCompound? {
        return bioactiveCompoundDao.getBioactiveCompoundById(id)
    }

    // Get all BioactiveCompounds by name
    suspend fun getBioactiveCompoundByName(name: String): List<BioactiveCompound> {
        return bioactiveCompoundDao.getBioactiveCompoundByName(name)
    }

    // Get all BioactiveCompounds by unitId
    suspend fun getBioactiveCompoundByUnitId(unitId: Int): List<BioactiveCompound> {
        return bioactiveCompoundDao.getBioactiveCompoundByUnitId(unitId)
    }

    // Get all BioactiveCompounds
    suspend fun getAllBioactiveCompounds(): List<BioactiveCompound> {
        return bioactiveCompoundDao.getAllBioactiveCompounds()
    }

    // Delete a BioactiveCompound by id
    suspend fun deleteBioactiveCompoundById(id: Int): Int {
        return bioactiveCompoundDao.deleteBioactiveCompoundById(id)
    }

    // Delete a BioactiveCompound by name
    suspend fun deleteBioactiveCompoundByName(name: String): Int {
        return bioactiveCompoundDao.deleteBioactiveCompoundByName(name)
    }

    // Update a BioactiveCompound by id
    suspend fun updateBioactiveCompoundById(
        id: Int,
        name: String,
        unitId: Int
    ): Int {
        return bioactiveCompoundDao.updateBioactiveCompoundById(id, name, unitId)
    }
}

class ItemBioactiveCompoundRepository @Inject constructor(private val itemBioactiveCompoundDao: ItemBioactiveCompoundDao) {
    // Insert a new ItemBioactiveCompound
    suspend fun insertItemBioactiveCompound(itemBioactiveCompound: ItemBioactiveCompound): Long {
        return itemBioactiveCompoundDao.insertItemBioactiveCompound(itemBioactiveCompound)
    }

    // Insert multiple ItemBioactiveCompounds
    suspend fun insertAllItemBioactiveCompounds(itemBioactiveCompounds: List<ItemBioactiveCompound>): List<Long> {
        return itemBioactiveCompoundDao.insertAllItemBioactiveCompounds(itemBioactiveCompounds)
    }

    // Get ItemBioactiveCompound by id
    suspend fun getItemBioactiveCompoundById(id: Int): ItemBioactiveCompound? {
        return itemBioactiveCompoundDao.getItemBioactiveCompoundById(id)
    }

    // Get all ItemBioactiveCompounds by itemId
    suspend fun getItemBioactiveCompoundByItemId(itemId: Int): List<ItemBioactiveCompound> {
        return itemBioactiveCompoundDao.getItemBioactiveCompoundByItemId(itemId)
    }

    // Get all ItemBioactiveCompounds by BioactiveCompoundId
    suspend fun getItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId: Int): List<ItemBioactiveCompound> {
        return itemBioactiveCompoundDao.getItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId)
    }

    // Get all ItemBioactiveCompounds by itemId and BioactiveCompoundId
    suspend fun getItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int
    ): List<ItemBioactiveCompound> {
        return itemBioactiveCompoundDao.getItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
            itemId,
            bioactiveCompoundId
        )
    }

    // Delete ItemBioactiveCompound by id
    suspend fun deleteItemBioactiveCompoundById(id: Int): Int {
        return itemBioactiveCompoundDao.deleteItemBioactiveCompoundById(id)
    }

    // Delete ItemBioactiveCompound by itemId
    suspend fun deleteItemBioactiveCompoundByItemId(itemId: Int): Int {
        return itemBioactiveCompoundDao.deleteItemBioactiveCompoundByItemId(itemId)
    }

    // Delete ItemBioactiveCompound by BioactiveCompoundId
    suspend fun deleteItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId: Int): Int {
        return itemBioactiveCompoundDao.deleteItemBioactiveCompoundByBioactiveCompoundId(bioactiveCompoundId)
    }

    // Delete ItemBioactiveCompound by itemId and BioactiveCompoundId
    suspend fun deleteItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int
    ): Int {
        return itemBioactiveCompoundDao.deleteItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
            itemId,
            bioactiveCompoundId
        )
    }

    // Update ItemBioactiveCompound by id
    suspend fun updateItemBioactiveCompoundById(
        id: Int,
        itemId: Int,
        bioactiveCompoundId: Int,
        amount: Float
    ): Int {
        return itemBioactiveCompoundDao.updateItemBioactiveCompoundById(id, itemId, bioactiveCompoundId, amount)
    }

    // Update ItemBioactiveCompound by itemId and BioactiveCompoundId
    suspend fun updateItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
        itemId: Int,
        bioactiveCompoundId: Int,
        amount: Float
    ): Int {
        return itemBioactiveCompoundDao.updateItemBioactiveCompoundByItemIdAndBioactiveCompoundId(
            itemId,
            bioactiveCompoundId,
            amount
        )
    }
}

class ItemWithBioactiveCompoundsRepository @Inject constructor(private val itemWithBioactiveCompoundsDao: ItemWithBioactiveCompoundsDao) {
    suspend fun getItemWithBioactiveCompounds(itemId: Int): ItemWithBioactiveCompounds {
        return itemWithBioactiveCompoundsDao.getItemWithBioactiveCompounds(itemId)
    }
}