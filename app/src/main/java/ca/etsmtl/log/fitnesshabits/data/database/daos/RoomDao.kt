package ca.etsmtl.log.fitnesshabits.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ca.etsmtl.log.fitnesshabits.data.database.entities.NutritionItem
import ca.etsmtl.log.fitnesshabits.data.database.entities.ItemServing
import ca.etsmtl.log.fitnesshabits.data.database.entities.Serving
import ca.etsmtl.log.fitnesshabits.data.database.entities.MacronutrientType
import ca.etsmtl.log.fitnesshabits.data.database.entities.NutritionItemMacronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.NutritionType
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationFactor
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {
    //Insert new item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(nutritionItem: NutritionItem)

    //Get all items
    @Query("SELECT * FROM nutritionItem")
    fun getAllItems(): Flow<List<NutritionItem>>

    //Get one item
    @Query("SELECT * FROM nutritionItem WHERE itemId = :id")
    fun getOneItem(id: Int): NutritionItem

    //Delete one item by itemId
    @Query("DELETE FROM nutritionItem WHERE itemId = :id")
    suspend fun deleteItem(id: Int)

    //Get all drinks
    @Query("SELECT * FROM nutritionItem WHERE typeId = 1")
    fun getAllDrinks(): Flow<List<NutritionItem>>

    //Get one drink
    @Query("SELECT * FROM nutritionItem WHERE itemId = :id AND typeId = 1")
    fun getOneDrink(id: Int): NutritionItem

    //GetHydrationFactor
    @Query("SELECT itemId, itemName, hydrationDescription, hydrationFactor FROM " +
            "NutritionItem AS NI JOIN HydrationFactor AS HF ON NI.hydrationId = HF.hydrationID " +
            "WHERE NI.itemId = :id")
    fun getHydrationFactor(id: Int): NutritionItem

    //GetNutritionType
    @Query("SELECT itemId, itemName, typeName FROM " +
            "NutritionItem AS NI JOIN NutritionType AS NT ON NI.typeId = NT.typeID " +
            "WHERE NI.itemId = :id")
    fun getNutritionType(id: Int): NutritionItem

    //GetNutritionMacros
    @Query("SELECT itemId, itemName, macronutrientTypeName, macronutrientTypeID, macronutrientAmount, macronutrientDefaultUnitID FROM " +
            "NutritionItem AS NI JOIN NutritionItemMacronutrient AS NIM ON NI.itemId = NIM.itemId JOIN " +
            "MacronutrientType AS MT ON NIM.macronutrientTypeID = MT.macronutrientTypeID WHERE NI.itemId = :id")
    fun getItemMacros(id: Int): Flow<List<NutritionItem>>


    //GetServings
    @Query("SELECT itemId, itemName, baseServingMultiplier, servingName, servingAmount, servingUnitId FROM " +
            "ItemServing, Serving, NutritionItem WHERE NutritionItem.itemId = :id AND " +
            "NutritionItem.itemId = ItemServing.itemId AND ItemServing.servingId = Serving.servingId")
    fun getServings(id: Int): Flow<List<NutritionItem>>

    @Query("SELECT itemId, itemName, baseServingMultiplier, servingName, servingAmount, servingUnitId FROM " +
            "NutritionItem AS NI JOIN ItemServing AS ISE ON NI.itemId = ISE.itemId JOIN " +
            "Serving AS S ON ISE.servingId = S.servingId WHERE NI.itemId = :id")
    fun getServings2(id: Int): Flow<List<NutritionItem>>
}