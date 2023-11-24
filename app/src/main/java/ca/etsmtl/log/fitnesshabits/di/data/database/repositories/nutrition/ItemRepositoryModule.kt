package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.BioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemBioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMacronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ServingDao
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ItemRepositoryModule {
    @Provides
    fun provideItemRepository(
        itemDao: ItemDao,
        servingDao: ServingDao,
        itemServingDao: ItemServingDao,
        itemMacronutrientDao: ItemMacronutrientDao,
        micronutrientDao: MicronutrientDao,
        itemMicronutrientDao: ItemMicronutrientDao,
        bioactiveCompoundDao: BioactiveCompoundDao,
        itemBioactiveCompoundDao: ItemBioactiveCompoundDao,
    ): ItemRepository {
        return ItemRepository(
            itemDao,
            servingDao,
            itemServingDao,
            itemMacronutrientDao,
            micronutrientDao,
            itemMicronutrientDao,
            bioactiveCompoundDao,
            itemBioactiveCompoundDao
        )
    }
}