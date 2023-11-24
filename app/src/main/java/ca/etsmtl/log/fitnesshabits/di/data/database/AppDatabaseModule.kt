package ca.etsmtl.log.fitnesshabits.di.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.etsmtl.log.fitnesshabits.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val insertMicronutrients =
                "insert into Micronutrient Values \n" +
                        "(1, \"Vitamin A\", 2),\n" +
                        "(2, \"Vitamin C\", 3),\n" +
                        "(3, \"Vitamin D\", 2),\n" +
                        "(4, \"Vitamin E\", 3),\n" +
                        "(5, \"Vitamin K\", 2),\n" +
                        "(6, \"Vitamin B1 (Thiamine)\", 3),\n" +
                        "(7, \"Vitamin B2 (Riboflavin)\", 3),\n" +
                        "(8, \"Vitamin B3 (Niacin)\", 3),\n" +
                        "(9, \"Vitamin B5 (Pantothenic acid)\", 3),\n" +
                        "(10, \"Vitamin B6\", 3),\n" +
                        "(11, \"Vitamin B7 (Biotin)\", 2),\n" +
                        "(12, \"Vitamin B9 (Folate)\", 2),\n" +
                        "(13, \"Vitamin B12\", 2),\n" +
                        "(14, \"Calcium\", 3),\n" +
                        "(15, \"Iron\", 3),\n" +
                        "(16, \"Magnesium\", 3),\n" +
                        "(17, \"Phosphorus\", 3),\n" +
                        "(18, \"Potassium\", 3),\n" +
                        "(19, \"Sodium\", 3),\n" +
                        "(20, \"Zinc\", 3),\n" +
                        "(21, \"Copper\", 2),\n" +
                        "(22, \"Manganese\", 3),\n" +
                        "(23, \"Selenium\", 2),\n" +
                        "(24, \"Chromium\", 2),\n" +
                        "(25, \"Molybdenum\", 2),\n" +
                        "(26, \"Chloride\", 3),\n" +
                        "(27, \"Iodine\", 2)\n"
            val insertBioactiveCompound =
                "insert into BioactiveCompound Values \n" +
                        "(1, \"Omega-3 fatty acids\", 3),\n" +
                        "(2, \"Omega-6 fatty acids\", 3),\n" +
                        "(3, \"Flavonoids\", 3), \n" +
                        "(4, \"Carotenoids\", 3),\n" +
                        "(5, \"Polyphenols\", 3),\n" +
                        "(6, \"Phytosterols\", 3),\n" +
                        "(7, \"Glucosinolates\", 3),\n" +
                        "(8, \"Saponins\", 3),\n" +
                        "(9, \"Capsaicin\", 3),\n" +
                        "(10, \"Curcumin\", 3),\n" +
                        "(11, \"Catechins\", 3),\n" +
                        "(12, \"Resveratrol\", 3),\n" +
                        "(13, \"Lycopene\", 3),\n" +
                        "(14, \"Lutein\", 3),\n" +
                        "(15, \"Zeaxanthin\", 3),\n" +
                        "(16, \"Isoflavones\", 3),\n" +
                        "(17, \"Anthocyanins\", 3),\n" +
                        "(18, \"Allicin\", 3),\n" +
                        "(19, \"Sulforaphane\", 3),\n" +
                        "(20, \"Quercetin\", 3),\n" +
                        "(21, \"Gingerol\", 3),\n" +
                        "(22, \"Piperine\", 3),\n" +
                        "(23, \"Caffeine\", 3),\n" +
                        "(24, \"EGCG (Epigallocatechin gallate)\", 3)\n"
            val insertItems =
                "INSERT INTO Item (id, name, description, typeId, hydrationIndexId) VALUES \n" +
                        "(1, 'Cola', 'Un soda sucré gazeux', 0, 0),\n" +
                        "(2, 'Café', 'Boisson infusée préparée à partir de grains de café', 0, 1),\n" +
                        "(3, 'Thé Vert', 'Thé fait à partir de feuilles non oxydées', 0, 2),\n" +
                        "(4, 'Tisane', 'Infusion d''herbes, d''épices ou d''autres matières végétales', 0, 3),\n" +
                        "(5, 'Eau de Coco', 'Liquide clair à l''intérieur des noix de coco', 0, 4),\n" +
                        "(6, 'Gatorade', 'Boisson conçue pour réhydrater', 0, 3),\n" +
                        "(7, 'Jus de pomme', 'Une boisson faite à partir de l''extraction de fruits frais', 0, 2),\n" +
                        "(8, 'Boisson Énergisante', 'Un type de boisson contenant des composés stimulants', 0, 1),\n" +
                        "(9, 'Lait', '2%', 0, 3),\n" +
                        "(10, 'Lait d''Amande', 'Lait végétal fait à partir d''amandes avec une saveur de noix', 0, 3),\n" +
                        "(11, 'Jus de d''orange', 'Orange fraichement pressé', 0, 2),\n" +
                        "(12, 'Bubly', 'Saveur mangue', 0, 2),\n" +
                        "(13, 'Perrier', 'Cochonnerie', 0, 3),\n" +
                        "(14, 'Eau', '', 0, 4)\n"
            val insertItemMacronutrients =
                "INSERT INTO ItemMacronutrient (itemId, macronutrientId, amount) VALUES " +
                        "(1, 0, 150),\n" +
                        "(1, 1, 0),\n" +
                        "(1, 2, 0),\n" +
                        "(1, 3, 0),\n" +
                        "(1, 4, 0),\n" +
                        "(1, 5, 0),\n" +
                        "(1, 6, 39),\n" +
                        "(1, 7, 0),\n" +
                        "(1, 8, 39),\n" +
                        "(1, 9, 39),\n" +
                        "(1, 10, 0),\n" +
                        "(1, 11, 15),\n" +
                        "(1, 12, 0),\n" +
                        "(2, 0, 5),\n" +
                        "(2, 1, 0),\n" +
                        "(2, 2, 0),\n" +
                        "(2, 3, 0),\n" +
                        "(2, 4, 0),\n" +
                        "(2, 5, 0),\n" +
                        "(2, 6, 0),\n" +
                        "(2, 7, 0),\n" +
                        "(2, 8, 0),\n" +
                        "(2, 9, 0),\n" +
                        "(2, 10, 1),\n" +
                        "(2, 11, 5),\n" +
                        "(2, 12, 0),\n" +
                        "(3, 0, 0), \n" +
                        "(3, 1, 0), \n" +
                        "(3, 2, 0), \n" +
                        "(3, 3, 0), \n" +
                        "(3, 4, 0), \n" +
                        "(3, 5, 0), \n" +
                        "(3, 6, 0), \n" +
                        "(3, 7, 0), \n" +
                        "(3, 8, 0), \n" +
                        "(3, 9, 0), \n" +
                        "(3, 10, 0), \n" +
                        "(3, 11, 0), \n" +
                        "(3, 12, 0), \n" +
                        "(4, 0, 2), \n" +
                        "(4, 1, 0), \n" +
                        "(4, 2, 0), \n" +
                        "(4, 3, 0), \n" +
                        "(4, 4, 0), \n" +
                        "(4, 5, 0), \n" +
                        "(4, 6, 1), \n" +
                        "(4, 7, 0), \n" +
                        "(4, 8, 1), \n" +
                        "(4, 9, 0), \n" +
                        "(4, 10, 0), \n" +
                        "(4, 11, 0), \n" +
                        "(4, 12, 0), \n" +
                        "(5, 0, 45), \n" +
                        "(5, 1, 0), \n" +
                        "(5, 2, 0), \n" +
                        "(5, 3, 0), \n" +
                        "(5, 4, 0), \n" +
                        "(5, 5, 0), \n" +
                        "(5, 6, 11), \n" +
                        "(5, 7, 3), \n" +
                        "(5, 8, 6), \n" +
                        "(5, 9, 6), \n" +
                        "(5, 10, 2), \n" +
                        "(5, 11, 25), \n" +
                        "(5, 12, 0),\n" +
                        "(6, 0, 80), \n" +
                        "(6, 1, 0), \n" +
                        "(6, 2, 0), \n" +
                        "(6, 3, 0), \n" +
                        "(6, 4, 0), \n" +
                        "(6, 5, 0), \n" +
                        "(6, 6, 21), \n" +
                        "(6, 7, 0), \n" +
                        "(6, 8, 21), \n" +
                        "(6, 9, 21), \n" +
                        "(6, 10, 0), \n" +
                        "(6, 11, 160), \n" +
                        "(6, 12, 0), \n" +
                        "(7, 0, 95), \n" +
                        "(7, 1, 0), \n" +
                        "(7, 2, 0), \n" +
                        "(7, 3, 0), \n" +
                        "(7, 4, 0), \n" +
                        "(7, 5, 0), \n" +
                        "(7, 6, 25), \n" +
                        "(7, 7, 0), \n" +
                        "(7, 8, 19), \n" +
                        "(7, 9, 19), \n" +
                        "(7, 10, 0), \n" +
                        "(7, 11, 2), \n" +
                        "(7, 12, 0),\n" +
                        "(8, 0, 110), \n" +
                        "(8, 1, 0), \n" +
                        "(8, 2, 0), \n" +
                        "(8, 3, 0), \n" +
                        "(8, 4, 0), \n" +
                        "(8, 5, 0), \n" +
                        "(8, 6, 28), \n" +
                        "(8, 7, 0), \n" +
                        "(8, 8, 27), \n" +
                        "(8, 9, 27), \n" +
                        "(8, 10, 1), \n" +
                        "(8, 11, 200), \n" +
                        "(8, 12, 0),\n" +
                        "(9, 0, 103), \n" +
                        "(9, 1, 2.4), \n" +
                        "(9, 2, 1.5), \n" +
                        "(9, 3, 0.1), \n" +
                        "(9, 4, 0), \n" +
                        "(9, 5, 0.8), \n" +
                        "(9, 6, 12), \n" +
                        "(9, 7, 0), \n" +
                        "(9, 8, 12), \n" +
                        "(9, 9, 12), \n" +
                        "(9, 10, 8), \n" +
                        "(9, 11, 100), \n" +
                        "(9, 12, 24)," +
                        "(10, 0, 30), \n" +
                        "(10, 1, 2.5), \n" +
                        "(10, 2, 0), \n" +
                        "(10, 3, 0), \n" +
                        "(10, 4, 0.6), \n" +
                        "(10, 5, 1.5), \n" +
                        "(10, 6, 1), \n" +
                        "(10, 7, 1), \n" +
                        "(10, 8, 0), \n" +
                        "(10, 9, 0), \n" +
                        "(10, 10, 1), \n" +
                        "(10, 11, 150), \n" +
                        "(10, 12, 0),\n" +
                        "(11, 0, 112), \n" +
                        "(11, 1, 0.5), \n" +
                        "(11, 2, 0.1), \n" +
                        "(11, 3, 0), \n" +
                        "(11, 4, 0.1), \n" +
                        "(11, 5, 0.2), \n" +
                        "(11, 6, 26), \n" +
                        "(11, 7, 0.5), \n" +
                        "(11, 8, 21), \n" +
                        "(11, 9, 21), \n" +
                        "(11, 10, 2), \n" +
                        "(11, 11, 3), \n" +
                        "(11, 12, 0),\n" +
                        "(12, 0, 0), \n" +
                        "(12, 1, 0), \n" +
                        "(12, 2, 0), \n" +
                        "(12, 3, 0), \n" +
                        "(12, 4, 0), \n" +
                        "(12, 5, 0), \n" +
                        "(12, 6, 0), \n" +
                        "(12, 7, 0), \n" +
                        "(12, 8, 0), \n" +
                        "(12, 9, 0), \n" +
                        "(12, 10, 0), \n" +
                        "(12, 11, 1), \n" +
                        "(12, 12, 0),\n" +
                        "(13, 0, 0), \n" +
                        "(13, 1, 0), \n" +
                        "(13, 2, 0), \n" +
                        "(13, 3, 0), \n" +
                        "(13, 4, 0), \n" +
                        "(13, 5, 0), \n" +
                        "(13, 6, 0), \n" +
                        "(13, 7, 0), \n" +
                        "(13, 8, 0), \n" +
                        "(13, 9, 0), \n" +
                        "(13, 10, 0), \n" +
                        "(13, 11, 2), \n" +
                        "(13, 12, 0),\n" +
                        "(14, 0, 0), \n" +
                        "(14, 1, 0), \n" +
                        "(14, 2, 0), \n" +
                        "(14, 3, 0), \n" +
                        "(14, 4, 0), \n" +
                        "(14, 5, 0), \n" +
                        "(14, 6, 0), \n" +
                        "(14, 7, 0), \n" +
                        "(14, 8, 0), \n" +
                        "(14, 9, 0), \n" +
                        "(14, 10, 0), \n" +
                        "(14, 11, 0), \n" +
                        "(14, 12, 0)"
            val insertServings = "INSERT INTO Serving (size, name, amount, unitId) VALUES " +
                    "(1, 'Petite Tasse', 150, 0), \n" +
                    "(1, 'Tasse Moyenne', 250, 0), \n" +
                    "(1, 'Grande Tasse', 350, 0), \n" +
                    "(1, 'Petite Bouteille', 500, 0), \n" +
                    "(1, 'Bouteille Moyenne', 750, 0), \n" +
                    "(1, 'Grande Bouteille', 1, 1), \n" +
                    "(1, 'Shot', 30, 0), \n" +
                    "(1, 'Canette', 330, 0), \n" +
                    "(1, 'Verre', 200, 0), \n" +
                    "(1, 'Tasse de Thé', 100, 0)"
            val insertItemServings =
                "INSERT INTO ItemServing (itemId, servingId, baseServingMultiplier) VALUES " +
                        "(1, 8, 1.0), (1, 4, 1.5),\n" +
                        "(2, 2, 1.0), (2, 10, 0.6),\n" +
                        "(3, 10, 1.0), (3, 1, 0.7),\n" +
                        "(4, 1, 1.0), (4, 3, 1.5),\n" +
                        "(5, 5, 1.0), (5, 6, 0.8),\n" +
                        "(6, 6, 1.0), (6, 4, 0.5),\n" +
                        "(7, 9, 1.0), (7, 2, 0.8),\n" +
                        "(8, 7, 1.0), (8, 8, 1.2),\n" +
                        "(9, 4, 1.0), (9, 5, 1.3),\n" +
                        "(10, 6, 1.0), (10, 3, 0.4),\n" +
                        "(11, 9, 1.0), (11, 8, 1.2),\n" +
                        "(12, 8, 1.0), (12, 7, 0.3),\n" +
                        "(13, 6, 1.5), (13, 9, 1.0),\n" +
                        "(14, 5, 1.0), (14, 2, 1.2)"
            db.beginTransaction()
            try {
                db.execSQL(insertMicronutrients)
                db.execSQL(insertBioactiveCompound)
                db.execSQL(insertItems)
                db.execSQL(insertServings)
                db.execSQL(insertItemServings)
                db.execSQL(insertItemMacronutrients)

                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        Log.d("Room", "Database initialized")
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "FitnessHabitsDatabase"
        ).addCallback(roomCallback).build()
    }
}