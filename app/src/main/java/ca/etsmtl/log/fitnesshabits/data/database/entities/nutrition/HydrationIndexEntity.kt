package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HydrationIndex(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val description: String,
    @ColumnInfo val multiplier: Float
)