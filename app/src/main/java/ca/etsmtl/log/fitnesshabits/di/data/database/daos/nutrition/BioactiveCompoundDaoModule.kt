package ca.etsmtl.log.fitnesshabits.di.data.database.daos.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.BioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemBioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithBioactiveCompoundsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BioactiveCompoundDaoModule {
    @Provides
    fun provideBioactiveCompoundDao(appDatabase: AppDatabase): BioactiveCompoundDao {
        return appDatabase.bioactiveCompoundDao()
    }

    @Provides
    fun provideItemBioactiveCompoundDao(appDatabase: AppDatabase): ItemBioactiveCompoundDao {
        return appDatabase.itemBioactiveCompoundDao()
    }

    @Provides
    fun provideItemWithBioactiveCompoundsDao(appDatabase: AppDatabase): ItemWithBioactiveCompoundsDao {
        return appDatabase.itemWithBioactiveCompoundsDao()
    }
}