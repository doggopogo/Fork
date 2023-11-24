package ca.etsmtl.log.fitnesshabits.data.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.BioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemBioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ServingDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.BioactiveCompound
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemBioactiveCompound
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMacronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemMicronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.ItemServing
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Micronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving
import ca.etsmtl.log.fitnesshabits.data.enums.Macronutrient
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val servingDao: ServingDao,
    private val itemServingDao: ItemServingDao,
    private val itemMacronutrientDao: ItemMacronutrientDao,
    private val micronutrientDao: MicronutrientDao,
    private val itemMicronutrientDao: ItemMicronutrientDao,
    private val bioactiveCompoundDao: BioactiveCompoundDao,
    private val itemBioactiveCompoundDao: ItemBioactiveCompoundDao,
) {
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
        return itemDao.updateItemById(
            id,
            name,
            description,
            typeId,
            hydrationIndexId,
            alcoholContentId
        )
    }

    // Adds a new item along with everything else
    suspend fun insertNewItem(
        item: Item,
        serving: Serving,
        macronutrients: List<Pair<Macronutrient, Int>>,
        newMicronutrients: List<Pair<Micronutrient, Int>>,
        micronutrients: List<Pair<Micronutrient, Int>>,
        newBioactiveCompounds: List<Pair<BioactiveCompound, Int>>,
        bioactiveCompounds: List<Pair<BioactiveCompound, Int>>
    ) {
        val itemId = itemDao.insertItem(item)

        // Checks if serving already exists
        var servingId = servingDao.findServingIdByNameAndSize(
            size = serving.size,
            name = serving.name
        )
        if (servingId == null) {
            servingId = servingDao.insertServing(serving).toInt()
        }

        itemServingDao.insertItemServing(
            ItemServing(
                itemId = itemId.toInt(),
                servingId = servingId,
                baseServingMultiplier = 1.0f
            )
        )

        macronutrients.forEach { (macronutrient, amount) ->
            itemMacronutrientDao.insertItemMacronutrient(
                ItemMacronutrient(
                    itemId = itemId.toInt(),
                    macronutrientId = macronutrient.ordinal,
                    amount = amount
                )
            )
        }

        newMicronutrients.forEach { (micronutrient, amount) ->
            // Checks if micronutrient already exists
            var existingMicronutrients = micronutrientDao.getMicronutrientByName(micronutrient.name)
            var micronutrientId = -1
            if (existingMicronutrients.isEmpty()) {
                micronutrientId = micronutrientDao.insertMicronutrient(micronutrient).toInt()
            } else {
                micronutrientId = existingMicronutrients[0].id
            }
            itemMicronutrientDao.insertItemMicronutrient(
                ItemMicronutrient(
                    itemId = itemId.toInt(),
                    micronutrientId = micronutrientId,
                    amount = amount
                )
            )
        }

        micronutrients.forEach { (micronutrient, amount) ->
            itemMicronutrientDao.insertItemMicronutrient(
                ItemMicronutrient(
                    itemId = itemId.toInt(),
                    micronutrientId = micronutrient.id,
                    amount = amount
                )
            )
        }

        newBioactiveCompounds.forEach { (bioactiveCompound, amount) ->
            // Checks if bioactive compound already exists
            var existingBioactiveCompounds =
                bioactiveCompoundDao.getBioactiveCompoundByName(bioactiveCompound.name)
            var bioactiveCompoundId = -1
            if (existingBioactiveCompounds.isEmpty()) {
                bioactiveCompoundId =
                    bioactiveCompoundDao.insertBioactiveCompound(bioactiveCompound).toInt()
            } else {
                bioactiveCompoundId = existingBioactiveCompounds[0].id
            }
            itemBioactiveCompoundDao.insertItemBioactiveCompound(
                ItemBioactiveCompound(
                    itemId = itemId.toInt(),
                    bioactiveCompoundId = bioactiveCompoundId.toInt(),
                    amount = amount
                )
            )
        }

        bioactiveCompounds.forEach { (bioactiveCompound, amount) ->
            itemBioactiveCompoundDao.insertItemBioactiveCompound(
                ItemBioactiveCompound(
                    itemId = itemId.toInt(),
                    bioactiveCompoundId = bioactiveCompound.id,
                    amount = amount
                )
            )
        }
    }

    suspend fun getItemsDataByTypeId(typeId: NutritionType): HashMap<Int, ItemData> {
        val itemsDataMap = HashMap<Int, ItemData>()

        val items = itemDao.getItemByTypeId(typeId.ordinal)
        items.forEach { item ->
            val servingsWithMultiplier = mutableListOf<Pair<Serving, Float>>()
            val itemServings = itemServingDao.getItemServingByItemId(item.id)
            itemServings.forEach { itemServing ->
                val serving = servingDao.getServingById(itemServing.servingId)
                servingsWithMultiplier.add(Pair(serving!!, itemServing.baseServingMultiplier))
            }

            itemsDataMap[item.id] = ItemData(
                item = item,
                servings = servingsWithMultiplier,
                macronutrients = itemMacronutrientDao.getItemMacronutrientByItemId(item.id),
                //micronutrients = itemMicronutrientDao.getItemMicronutrientByItemId(item.id),
                //bioactiveCompounds = itemBioactiveCompoundDao.getItemBioactiveCompoundByItemId(item.id)
            )
        }
        return itemsDataMap
    }

    suspend fun getItemDataByItemId(itemId: Int): ItemData {
        val item = itemDao.getItemById(itemId)!!
        val servingsWithMultiplier = mutableListOf<Pair<Serving, Float>>()
        val itemServings = itemServingDao.getItemServingByItemId(item.id)
        itemServings.forEach { itemServing ->
            val serving = servingDao.getServingById(itemServing.servingId)
            servingsWithMultiplier.add(Pair(serving!!, itemServing.baseServingMultiplier))
        }

        return ItemData(
            item = item,
            servings = servingsWithMultiplier,
            macronutrients = itemMacronutrientDao.getItemMacronutrientByItemId(item.id),
            //micronutrients = itemMicronutrientDao.getItemMicronutrientByItemId(item.id),
            //bioactiveCompounds = itemBioactiveCompoundDao.getItemBioactiveCompoundByItemId(item.id)
        )
    }

    // Gets most commonly used items
    suspend fun getItemsDataOrderedByUsage(typeId: NutritionType): HashMap<Int, ItemData>{
        val itemsDataMap = HashMap<Int, ItemData>()

        val items = itemDao.getItemsOrderedByUsage(typeId.ordinal)
        items.forEach { item ->
            val servingsWithMultiplier = mutableListOf<Pair<Serving, Float>>()
            val itemServings = itemServingDao.getItemServingByItemId(item.id)
            itemServings.forEach { itemServing ->
                val serving = servingDao.getServingById(itemServing.servingId)
                servingsWithMultiplier.add(Pair(serving!!, itemServing.baseServingMultiplier))
            }

            itemsDataMap[item.id] = ItemData(
                item = item,
                servings = servingsWithMultiplier,
                macronutrients = itemMacronutrientDao.getItemMacronutrientByItemId(item.id),
                //micronutrients = itemMicronutrientDao.getItemMicronutrientByItemId(item.id),
                //bioactiveCompounds = itemBioactiveCompoundDao.getItemBioactiveCompoundByItemId(item.id)
            )
        }
        return itemsDataMap
    }
}

data class ItemData(
    val item: Item,
    val servings: List<Pair<Serving, Float>>,
    val macronutrients: List<ItemMacronutrient>,
    val micronutrients: List<ItemMicronutrient>? = null,
    val bioactiveCompounds: List<ItemBioactiveCompound>? = null
)