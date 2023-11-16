package ca.etsmtl.log.fitnesshabits.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.*
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.*
import ca.etsmtl.log.fitnesshabits.data.database.entities.Unit
import ca.etsmtl.log.fitnesshabits.data.database.daos.UnitDao

@Database(
    entities = [
        Log::class,

        Item::class,
        Type::class,
        HydrationIndex::class,
        AlcoholContent::class,

        Serving::class,
        ItemServing::class,

        Macronutrient::class,
        ItemMacronutrient::class,

        Micronutrient::class,
        ItemMicronutrient::class,

        BioactiveCompound::class,
        ItemBioactiveCompound::class,

        Unit::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    // region DAOs
    abstract fun logDao(): LogDao

    abstract fun itemDao(): ItemDao
    abstract fun typeDao(): TypeDao
    abstract fun hydrationIndexDao(): HydrationIndexDao
    abstract fun alcoholContentDao(): AlcoholContentDao

    abstract fun servingDao(): ServingDao
    abstract fun itemServingDao(): ItemServingDao
    abstract fun itemWithServingsDao(): ItemWithServingsDao

    abstract fun macronutrientDao(): MacronutrientDao
    abstract fun itemMacronutrientDao(): ItemMacronutrientDao
    abstract fun itemWithMacronutrientsDao(): ItemWithMacronutrientsDao

    abstract fun micronutrientDao(): MicronutrientDao
    abstract fun itemMicronutrientDao(): ItemMicronutrientDao
    abstract fun itemWithMicronutrientsDao(): ItemWithMicronutrientsDao

    abstract fun bioactiveCompoundDao(): BioactiveCompoundDao
    abstract fun itemBioactiveCompoundDao(): ItemBioactiveCompoundDao
    abstract fun itemWithBioactiveCompoundsDao(): ItemWithBioactiveCompoundsDao

    abstract fun unitDao(): UnitDao
    // endregion
}