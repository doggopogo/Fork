package ca.etsmtl.log.fitnesshabits.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = HydrationType::class,
            parentColumns = ["id"],
            childColumns = ["hydrationTypeId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HydrationConsumed(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "hydrationTypeId") val hydrationTypeId: Int,
    @ColumnInfo(name = "nbPortions") val nbPortions: Int,
    @ColumnInfo(name = "time") val time: Time,
    @ColumnInfo(name = "date") val date: Date,
)

@Entity
data class HydrationType(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "shortName") val shortName: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "portionType") val portionType: String,
    @ColumnInfo(name = "portionSize") val portionSize: Int,
    @ColumnInfo(name = "usualPortionSize") val usualPortionSize: Int,
    @ColumnInfo(name = "protein") val protein: Float?,
    @ColumnInfo(name = "carb") val carb: Float?,
    @ColumnInfo(name = "fiber") val fiber: Float?,
    @ColumnInfo(name = "fat") val fat: Float?,
)