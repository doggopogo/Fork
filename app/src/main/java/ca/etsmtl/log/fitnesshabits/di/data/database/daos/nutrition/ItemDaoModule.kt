package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ItemDaoModule {
    @Provides
    fun provideItemDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.itemDao()
    }
}