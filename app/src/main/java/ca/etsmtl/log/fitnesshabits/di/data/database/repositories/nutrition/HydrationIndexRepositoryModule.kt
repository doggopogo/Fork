package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.HydrationIndexDao
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.HydrationIndexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HydrationIndexRepositoryModule {
    @Provides
    fun provideHydrationIndexRepository(hydrationIndexDao: HydrationIndexDao): HydrationIndexRepository {
        return HydrationIndexRepository(hydrationIndexDao)
    }
}