package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.TypeDao
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.TypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object TypeRepositoryModule {
    @Provides
    fun provideTypeRepository(typeDao: TypeDao): TypeRepository {
        return TypeRepository(typeDao)
    }
}