package ca.etsmtl.log.fitnesshabits.di.data.database.daos

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.UnitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UnitDaoModule {
    @Provides
    fun provideUnitDao(appDatabase: AppDatabase): UnitDao {
        return appDatabase.unitDao()
    }
}