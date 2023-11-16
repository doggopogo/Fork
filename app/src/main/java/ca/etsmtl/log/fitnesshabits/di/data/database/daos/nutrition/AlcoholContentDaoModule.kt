package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.AlcoholContentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlcoholContentDaoModule {
    @Provides
    fun provideAlcoholContentDao(appDatabase: AppDatabase): AlcoholContentDao {
        return appDatabase.alcoholContentDao()
    }
}