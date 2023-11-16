package ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao) {
    // Insert a single item
    suspend fun insertItem(item: Item): Long {
        return itemDao.insertItem(item)
    }

    // Insert multiple items
    suspend fun insertAllItems(items: List<Item>): List<Long> {
        return itemDao.insertAllItems(items)
    }

    // Get an item by id
    suspend fun getItemById(id: Int): Item? {
        return itemDao.getItemById(id)
    }

    // Get all items by name
    suspend fun getItemByName(name: String): List<Item> {
        return itemDao.getItemByName(name)
    }

    // Get all items by typeId
    suspend fun getItemByTypeId(typeId: Int): List<Item> {
        return itemDao.getItemByTypeId(typeId)
    }

    // Get all items by hydrationIndexId
    suspend fun getItemByHydrationIndexId(hydrationIndexId: Int): List<Item> {
        return itemDao.getItemByHydrationIndexId(hydrationIndexId)
    }

    // Get all items where hydrationIndexId is not null
    suspend fun getItemWithHydrationIndex(): List<Item> {
        return itemDao.getItemWithHydrationIndex()
    }

    // Get all items by alcoholContentId
    suspend fun getItemByAlcoholContentId(alcoholContentId: Int): List<Item> {
        return itemDao.getItemByAlcoholContentId(alcoholContentId)
    }

    // Get all items where alcoholContentId is not null
    suspend fun getItemWithAlcoholContent(): List<Item> {
        return itemDao.getItemWithAlcoholContent()
    }

    // Get all items
    suspend fun getAllItems(): List<Item> {
        return itemDao.getAllItems()
    }

    // Delete an item by id
    suspend fun deleteItemById(id: Int): Int {
        return itemDao.deleteItemById(id)
    }

    // Update an item by id
    suspend fun updateItemById(
        id: Int,
        name: String,
        description: String?,
        typeId: Int,
        hydrationIndexId: Int?,
        alcoholContentId: Int?
    ): Int {
        return itemDao.updateItemById(id, name, description, typeId, hydrationIndexId, alcoholContentId)
    }
}