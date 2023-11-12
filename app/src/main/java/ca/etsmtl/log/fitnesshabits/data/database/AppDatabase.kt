package ca.etsmtl.log.fitnesshabits.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.etsmtl.log.fitnesshabits.data.database.daos.HydrationDao
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationType

// Annotate the class to be a Room database, declare the entities that belong in the database and set the version.
@Database(entities = [HydrationType::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // The DAO is abstract because Room will generate the implementation for you.
    abstract fun hydrationDao(): HydrationDao

    // Define a companion object, this allows us to add functions on the AppDatabase class.
    companion object {
        // @Volatile annotations your instance will keep visible to all threads.
        @Volatile private var INSTANCE: AppDatabase? = null

        // The function to get the database is a singleton to prevent having multiple instances of the database opened at the same time.
        fun getDatabase(context: Context): AppDatabase {
            // If the INSTANCE is not null, then return it, if it is, then create the database.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_habits_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this code snippet.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // Return instance; INSTANCE is the newly created database.
                instance
            }
        }
    }
}
