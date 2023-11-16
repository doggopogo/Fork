package ca.etsmtl.log.fitnesshabits.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Unit(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val symbol: String,
    //@ColumnInfo(name = "standardUnitId") val standardUnitId: Int,
    //@ColumnInfo(name = "standardUnitConversionRate") val standardUnitConversionRate: Float,
)

/*
@Entity
data class UnitConversion(
    @PrimaryKey val conversionId: Int,
    @ColumnInfo(name = "fromUnitId") val fromUnitId: Int,
    @ColumnInfo(name = "toUnitId") val toUnitId: Int,
    @ColumnInfo(name = "conversionFactor") val conversionFactor: Float,
)
*/