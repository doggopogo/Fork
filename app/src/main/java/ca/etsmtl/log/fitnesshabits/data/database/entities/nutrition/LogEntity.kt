package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Log(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val itemId: Int,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val servingMultiplier: Float,
)