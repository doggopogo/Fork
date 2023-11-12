package ca.etsmtl.log.fitnesshabits.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class NutritionLog(
    @PrimaryKey val logId: Int,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "timeAndData") val timeAndData: Timestamp,
    @ColumnInfo(name = "servingMultiplier") val servingMultiplier: Float,
)

@Entity
data class NutritionItem(
    @PrimaryKey val itemId: Int,
    @ColumnInfo(name = "itemName") val itemName: String,
    @ColumnInfo(name = "itemDescription") val itemDescription: String?,
    @ColumnInfo(name = "typeId") val typeId: Int,
    @ColumnInfo(name = "hydrationId") val hydrationId: Int?,
    @ColumnInfo(name = "alcoholId") val alcoholId: Int?,
)

@Entity
data class ItemServing(
    @PrimaryKey val itemServingId: Int,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "servingId") val servingId: Int,
    @ColumnInfo(name = "baseServingMultiplier") val baseServingMultiplier: Float,
)

@Entity
data class Serving(
    @PrimaryKey val servingId: Int,
    @ColumnInfo(name = "servingName") val servingName: String,
    @ColumnInfo(name = "servingAmount") val servingAmount: Float,
    @ColumnInfo(name = "servingUnitId") val servingUnitId: Int,
)

@Entity
data class Unit(
    @PrimaryKey val unitId: Int,
    @ColumnInfo(name = "unitName") val unitName: String,
    @ColumnInfo(name = "unitSymbol") val unitSymbol: String,
    @ColumnInfo(name = "standardUnitId") val standardUnitId: Int,
    @ColumnInfo(name = "standardUnitConversionRate") val standardUnitConversionRate: Float,
)

@Entity
data class UnitConversion(
    @PrimaryKey val conversionId: Int,
    @ColumnInfo(name = "fromUnitId") val fromUnitId: Int,
    @ColumnInfo(name = "toUnitId") val toUnitId: Int,
    @ColumnInfo(name = "conversionFactor") val conversionFactor: Float,
)

@Entity
data class MacronutrientType(
    @PrimaryKey val macronutrientTypeID: Int,
    @ColumnInfo(name = "macronutrientTypeName") val macronutrientTypeName: String,
    @ColumnInfo(name = "macronutrientDefaultUnitID") val macronutrientDefaultUnitID: Int,
)

@Entity
data class NutritionItemMacronutrient(
    @PrimaryKey val nutritionItemMacronutrientID: Int,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "macronutrientTypeID") val macronutrientTypeID: Int,
    @ColumnInfo(name = "macronutrientAmount") val macronutrientAmount: Float,
)

@Entity
data class MicronutrientType(
    @PrimaryKey val micronutrientTypeID: Int,
    @ColumnInfo(name = "micronutrientTypeName") val micronutrientTypeName: String,
    @ColumnInfo(name = "micronutrientDefaultUnitID") val micronutrientDefaultUnitID: Int,
)

@Entity
data class NutritionItemMicronutrient(
    @PrimaryKey val nutritionItemMicronutrientID: Int,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "micronutrientTypeID") val micronutrientTypeID: Int,
    @ColumnInfo(name = "micronutrientAmount") val micronutrientAmount: Float,
)

@Entity
data class ActiveCompoundType(
    @PrimaryKey val activeCompoundTypeID: Int,
    @ColumnInfo(name = "activeCompoundTypeName") val activeCompoundTypeName: String,
    @ColumnInfo(name = "activeCompoundDefaultUnitID") val activeCompoundDefaultUnitID: Int,
)

@Entity
data class NutritionItemActiveCompound(
    @PrimaryKey val nutritionItemActiveCompoundID: Int,
    @ColumnInfo(name = "itemId") val itemId: Int,
    @ColumnInfo(name = "activeCompoundTypeID") val activeCompoundTypeID: Int,
    @ColumnInfo(name = "activeCompoundAmount") val activeCompoundAmount: Float,
)

@Entity
data class NutritionType(
    @PrimaryKey val typeID: Int,
    @ColumnInfo(name = "typeName") val typeName: String,
)

@Entity
data class HydrationFactor(
    @PrimaryKey val hydrationID: Int,
    @ColumnInfo(name = "hydrationDescription") val hydrationDescription: String,
    @ColumnInfo(name = "hydrationFactor") val hydrationFactor: Float,
)

@Entity
data class AlcoholContent(
    @PrimaryKey val alcoholID: Int,
    @ColumnInfo(name = "alcoholPercentage") val alcoholPercentage: Float,
)