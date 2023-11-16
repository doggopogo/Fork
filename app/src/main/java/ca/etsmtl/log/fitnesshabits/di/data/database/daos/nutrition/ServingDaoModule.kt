package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemServingDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithServingsDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ServingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServingDaoModule {
    @Provides
    fun provideServingDao(appDatabase: AppDatabase): ServingDao {
        return appDatabase.servingDao()
    }

    @Provides
    fun provideItemServingDao(appDatabase: AppDatabase): ItemServingDao {
        return appDatabase.itemServingDao()
    }

    @Provides
    fun provideItemWithServingsDao(appDatabase: AppDatabase): ItemWithServingsDao {
        return appDatabase.itemWithServingsDao()
    }
}