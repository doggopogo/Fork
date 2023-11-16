package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.MicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemMicronutrientDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithMicronutrientsDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MicronutrientDaoModule {
    @Provides
    fun provideMicronutrientDao(appDatabase: AppDatabase): MicronutrientDao {
        return appDatabase.micronutrientDao()
    }

    @Provides
    fun provideItemMicronutrientDao(appDatabase: AppDatabase): ItemMicronutrientDao {
        return appDatabase.itemMicronutrientDao()
    }

    @Provides
    fun provideItemWithMicronutrientsDao(appDatabase: AppDatabase): ItemWithMicronutrientsDao {
        return appDatabase.itemWithMicronutrientsDao()
    }
}