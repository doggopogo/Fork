package ca.etsmtl.log.fitnesshabits.data.database.entities;
//https://www.youtube.com/watch?v=-LNg-K7SncM
//https://developer.android.com/training/data-storage/room?hl=fr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity
data class Sleep(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "hourSlept") val hourSlept: Float?,
    @ColumnInfo(name = "sleepQuality") val sleepQuality: String?,
    @ColumnInfo(name = "moodAtWake") val moodAtWake: String?,
    @ColumnInfo(name = "timesWokenUp") val timesWokenUp: Int?
)

@Entity
data class BioBreak(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "poopType") val poopType: String?,
    @ColumnInfo(name = "peeColor") val peeColor: String?,
    @ColumnInfo(name = "amount") val amount: Int?,
    @ColumnInfo(name = "unit") val unit: String?
)

@Entity
data class Diabetes(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "bloodSugar") val bloodSugar: Float?
)

@Entity
data class PhysicalActivity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "duration") val duration: Float?,
    @ColumnInfo(name = "intensity") val intensity: Int?
)

@Entity
data class Notification(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "recurring") val recurring: Boolean?,
    @ColumnInfo(name = "recurringFrequency") val recurringFrequency: Int?,
    @ColumnInfo(name = "recurringHour") val recurringHour: Time?
)

@Entity
data class Users(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "dob") val dob: Date?,
    @ColumnInfo(name = "height") val height: Float?,
    @ColumnInfo(name = "weight") val weight: Float?,
    @ColumnInfo(name = "sex") val sex: String?
)