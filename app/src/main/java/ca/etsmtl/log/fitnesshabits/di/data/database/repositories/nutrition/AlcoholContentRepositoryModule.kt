package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.AlcoholContentDao
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.AlcoholContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AlcoholContentRepositoryModule {
    @Provides
    fun provideAlcoholContentRepository(alcoholContentDao: AlcoholContentDao): AlcoholContentRepository {
        return AlcoholContentRepository(alcoholContentDao)
    }
}