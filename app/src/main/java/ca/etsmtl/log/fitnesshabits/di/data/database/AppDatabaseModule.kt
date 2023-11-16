package ca.etsmtl.log.fitnesshabits.di.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        Log.d("ZHAO","database init")
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "FitnessHabitsDatabase"
        )
            //.createFromAsset("FitnessHabitsDatabase.db")
            .build()
    }
}
