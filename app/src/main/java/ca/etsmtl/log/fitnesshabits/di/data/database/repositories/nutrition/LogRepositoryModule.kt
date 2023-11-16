package ca.etsmtl.log.fitnesshabits.di.data.database.repositories.nutrition

import ca.etsmtl.log.fitnesshabits.data.database.daos.nutrition.LogDao
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.LogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object LogRepositoryModule {
    @Provides
    fun provideLogRepository(logDao: LogDao): LogRepository {
        return LogRepository(logDao)
    }
}