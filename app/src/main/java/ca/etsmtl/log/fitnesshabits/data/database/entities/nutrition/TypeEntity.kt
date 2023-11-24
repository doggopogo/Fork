package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String
)