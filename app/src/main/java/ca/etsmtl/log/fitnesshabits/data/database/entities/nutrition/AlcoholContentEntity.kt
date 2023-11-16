package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlcoholContent(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val percentage: Float
)