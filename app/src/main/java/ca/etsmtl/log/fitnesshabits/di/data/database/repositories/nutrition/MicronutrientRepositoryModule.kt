package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMicronutrientsDao
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.MicronutrientRepository
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.ItemMicronutrientRepository
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.ItemWithMicronutrientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MicronutrientRepositoryModule {
    @Provides
    fun provideMicronutrientRepository(micronutrientDao: MicronutrientDao): MicronutrientRepository {
        return MicronutrientRepository(micronutrientDao)
    }

    @Provides
    fun provideItemMicronutrientRepository(itemMicronutrientDao: ItemMicronutrientDao): ItemMicronutrientRepository {
        return ItemMicronutrientRepository(itemMicronutrientDao)
    }

    @Provides
    fun provideItemWithMicronutrientsRepository(itemWithMicronutrientsDao: ItemWithMicronutrientsDao): ItemWithMicronutrientsRepository {
        return ItemWithMicronutrientsRepository(itemWithMicronutrientsDao)
    }
}