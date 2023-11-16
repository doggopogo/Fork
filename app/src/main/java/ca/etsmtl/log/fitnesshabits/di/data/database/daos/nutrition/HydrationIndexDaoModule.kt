package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.HydrationIndexDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HydrationIndexDaoModule {
    @Provides
    fun provideHydrationIndexDao(appDatabase: AppDatabase): HydrationIndexDao {
        return appDatabase.hydrationIndexDao()
    }
}