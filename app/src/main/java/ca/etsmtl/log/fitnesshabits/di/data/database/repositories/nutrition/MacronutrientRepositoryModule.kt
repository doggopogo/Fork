package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMacronutrientsDao
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.MacronutrientRepository
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.ItemMacronutrientRepository
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.ItemWithMacronutrientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MacronutrientRepositoryModule {
    @Provides
    fun provideMacronutrientRepository(macronutrientDao: MacronutrientDao): MacronutrientRepository {
        return MacronutrientRepository(macronutrientDao)
    }

    @Provides
    fun provideItemMacronutrientRepository(itemMacronutrientDao: ItemMacronutrientDao): ItemMacronutrientRepository {
        return ItemMacronutrientRepository(itemMacronutrientDao)
    }

    @Provides
    fun provideItemWithMacronutrientsRepository(itemWithMacronutrientsDao: ItemWithMacronutrientsDao): ItemWithMacronutrientsRepository {
        return ItemWithMacronutrientsRepository(itemWithMacronutrientsDao)
    }
}