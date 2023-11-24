package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithServingsDao
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ServingRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemServingRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemWithServingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ServingRepositoryModule {
    @Provides
    fun provideServingRepository(servingDao: ServingDao): ServingRepository {
        return ServingRepository(servingDao)
    }

    @Provides
    fun provideItemServingRepository(itemServingDao: ItemServingDao): ItemServingRepository {
        return ItemServingRepository(itemServingDao)
    }

    @Provides
    fun provideItemWithServingsRepository(itemWithServingsDao: ItemWithServingsDao): ItemWithServingsRepository {
        return ItemWithServingsRepository(itemWithServingsDao)
    }
}