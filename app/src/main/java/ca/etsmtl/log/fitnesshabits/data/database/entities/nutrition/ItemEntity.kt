package ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String?,
    @ColumnInfo val typeId: Int,
    @ColumnInfo val hydrationIndexId: Int?,
    @ColumnInfo val alcoholContentId: Int?
)