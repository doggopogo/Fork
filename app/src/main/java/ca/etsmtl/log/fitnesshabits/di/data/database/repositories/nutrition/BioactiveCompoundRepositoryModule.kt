package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.BioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemBioactiveCompoundDao
import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.ItemWithBioactiveCompoundsDao
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.BioactiveCompoundRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemBioactiveCompoundRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemWithBioactiveCompoundsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object BioactiveCompoundRepositoryModule {
    @Provides
    fun provideBioactiveCompoundRepository(bioactiveCompoundDao: BioactiveCompoundDao): BioactiveCompoundRepository {
        return BioactiveCompoundRepository(bioactiveCompoundDao)
    }

    @Provides
    fun provideItemBioactiveCompoundRepository(itemBioactiveCompoundDao: ItemBioactiveCompoundDao): ItemBioactiveCompoundRepository {
        return ItemBioactiveCompoundRepository(itemBioactiveCompoundDao)
    }

    @Provides
    fun provideItemWithBioactiveCompoundsRepository(itemWithBioactiveCompoundsDao: ItemWithBioactiveCompoundsDao): ItemWithBioactiveCompoundsRepository {
        return ItemWithBioactiveCompoundsRepository(itemWithBioactiveCompoundsDao)
    }
}