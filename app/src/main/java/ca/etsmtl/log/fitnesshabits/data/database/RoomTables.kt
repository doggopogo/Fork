package ca.etsmtl.log.fitnesshabits.data.database;
//https://www.youtube.com/watch?v=-LNg-K7SncM
//https://developer.android.com/training/data-storage/room?hl=fr

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity
data class Hydration(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "abbreviation") val abbreviation: String?,
    @ColumnInfo(name = "protein") val protein: Float?,
    @ColumnInfo(name = "carb") val carb: Float?,
    @ColumnInfo(name = "fiber") val fiber: Float?,
    @ColumnInfo(name = "fat") val fat: Float?,
    @ColumnInfo(name = "size") val size: Int?,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "descriptionFr") val descriptionFr: String?
)

@Entity
data class Sleep(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "hourSlept") val hourSlept: Float?,
    @ColumnInfo(name = "sleepQuality") val sleepQuality: String?,
    @ColumnInfo(name = "moodAtWake") val moodAtWake: String?,
    @ColumnInfo(name = "timesWokenUp") val timesWokenUp: Int?
)

@Entity
data class BioBreak(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "poopType") val poopType: String?,
    @ColumnInfo(name = "peeColor") val peeColor: String?,
    @ColumnInfo(name = "amount") val amount: Int?,
    @ColumnInfo(name = "unit") val unit: String?
)

@Entity
data class Diabetes(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "bloodSugar") val bloodSugar: Float?
)

@Entity
data class PhysicalActivity(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "duration") val duration: Float?,
    @ColumnInfo(name = "intensity") val intensity: Int?
)

@Entity
data class Notification(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "recurring") val recurring: Boolean?,
    @ColumnInfo(name = "recurringFrequency") val recurringFrequency: Int?,
    @ColumnInfo(name = "recurringHour") val recurringHour: Time?
)

@Entity
data class Alcohol(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "volume") val volume: Float?,
    @ColumnInfo(name = "unit") val unit: String?,
    @ColumnInfo(name = "format") val format: String?,
    @ColumnInfo(name = "percentAlcohol") val percentAlcohol: Float?,
    @ColumnInfo(name = "calorie") val calorie: Int?,
    @ColumnInfo(name = "protein") val protein: Float?,
    @ColumnInfo(name = "carb") val carb: Float?,
    @ColumnInfo(name = "fiber") val fiber: Float?,
    @ColumnInfo(name = "fat") val fat: Float?
)

@Entity
data class MedicationSupplement(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "dosage") val dosage: Float?,
    @ColumnInfo(name = "amount") val amount: Int?
)

@Entity
data class Users(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "lastName") val lastName: String?,
    @ColumnInfo(name = "dob") val dob: Date?,
    @ColumnInfo(name = "height") val height: Float?,
    @ColumnInfo(name = "weight") val weight: Float?,
    @ColumnInfo(name = "sex") val sex: String?
)

@Entity
data class Nutrition(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "unit") val unit: String?,
    @ColumnInfo(name = "amount") val amount: Float?,
    @ColumnInfo(name = "calorie") val calorie: Float?,
    @ColumnInfo(name = "protein") val protein: Float?,
    @ColumnInfo(name = "carbs") val carbs: Float?,
    @ColumnInfo(name = "sugar") val sugar: Float?,
    @ColumnInfo(name = "fiber") val fiber: Float?,
    @ColumnInfo(name = "totalFat") val totalFat: Float?,
    @ColumnInfo(name = "saturatedFat") val saturatedFat: Float?,
    @ColumnInfo(name = "cholesterol") val cholesterol: Float?,
    @ColumnInfo(name = "calcium") val calcium: Float?,
    @ColumnInfo(name = "iron") val iron: Float?,
    @ColumnInfo(name = "sodium") val sodium: Float?,
    @ColumnInfo(name = "potassium") val potassium: Float?,
    @ColumnInfo(name = "magnesium") val magnesium: Float?,
    @ColumnInfo(name = "phosphorus") val phosphorus: Float?,
    @ColumnInfo(name = "thiamine") val thiamine: Float?,
    @ColumnInfo(name = "riboflavin") val riboflavin: Float?,
    @ColumnInfo(name = "descriptionEng") val descriptionEng: String?,
    @ColumnInfo(name = "descriptionFr") val descriptionFr: String?
)