package ca.etsmtl.log.fitnesshabits.di.data.database.repositories

import ca.etsmtl.log.fitnesshabits.data.database.daos.UnitDao
import ca.etsmtl.log.fitnesshabits.data.repositories.UnitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UnitRepositoryModule {
    @Provides
    fun provideUnitRepository(unitDao: UnitDao): UnitRepository {
        return UnitRepository(unitDao)
    }
}