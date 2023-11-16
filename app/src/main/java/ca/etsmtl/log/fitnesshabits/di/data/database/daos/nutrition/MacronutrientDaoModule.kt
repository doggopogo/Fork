package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMacronutrientsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MacronutrientDaoModule {
    @Provides
    fun provideMacronutrientDao(appDatabase: AppDatabase): MacronutrientDao {
        return appDatabase.macronutrientDao()
    }

    @Provides
    fun provideItemMacronutrientDao(appDatabase: AppDatabase): ItemMacronutrientDao {
        return appDatabase.itemMacronutrientDao()
    }

    @Provides
    fun provideItemWithMacronutrientsDao(appDatabase: AppDatabase): ItemWithMacronutrientsDao {
        return appDatabase.itemWithMacronutrientsDao()
    }
}