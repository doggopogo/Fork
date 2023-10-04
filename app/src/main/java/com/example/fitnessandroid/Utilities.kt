package com.example.fitnessandroid

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.fithabdata.FitHabUiState
import com.example.fitnessandroid.fithabdata.UserInfo
import com.example.fitnessandroid.homescreen.activity.Activity
import com.example.fitnessandroid.homescreen.activity.user.UserActivity
import com.example.fitnessandroid.homescreen.alcohol.Alcohol
import com.example.fitnessandroid.homescreen.alcohol.user.UserAlcohol
import com.example.fitnessandroid.homescreen.bloodSugar.user.UserBloodSugar
import com.example.fitnessandroid.homescreen.food.Food
import com.example.fitnessandroid.homescreen.food.user.UserFood
import com.example.fitnessandroid.homescreen.sleep.user.UserSleep
import com.example.fitnessandroid.homescreen.toilet.user.UserToilet
import com.example.fitnessandroid.homescreen.user.User
import com.example.fitnessandroid.homescreen.weight.user.UserWeight
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap
import kotlin.math.absoluteValue

object Utilities {
    fun String.getHashMapMultiplesValues() = this.removeFirstAndLastChar().split(",")
        .map { it.trim(',').split(":") }.associateTo(HashMap()) { it.first().removeFirstAndLastChar() to it.last() }

    fun String.getHashMapOneValue() = this.substring( 1, this.length - 1 )
        .split(":").chunked(2) { it[0].removeFirstAndLastChar() to it[1].removeFirstAndLastChar() }.toMap().toHashMap()

    fun String.toUpperCase() = this.replaceFirst(first(),first().uppercase()[0])

    private fun String.removeFirstAndLastChar() = this.substring(1, this.length - 1)

    private fun Map<String,Any>.toHashMap() : HashMap<String,Any> {
        val hash = hashMapOf<String,Any>()
        hash.putAll(this)
        return hash
    }

    fun Date.isInLastXDays(nbOfDay: Int) : Boolean {
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -nbOfDay)
        return this.after(cal.time)
    }

    fun Date.nbOfDaySinceToday() : Int {
        return TimeUnit.MILLISECONDS.toDays(this.time - Calendar.getInstance().timeInMillis).toInt().absoluteValue
    }

    fun Date.removeXDay(nbOfDay: Int) : Date {
        val cal: Calendar = Calendar.getInstance()
        cal.time = this
        cal.add(Calendar.DAY_OF_YEAR, -nbOfDay)
        return cal.time
    }

    fun dateAndTimeCalendar(date: Date, time:Date) :Date {
        val calendar = Calendar.getInstance()
        val calendarHM = Calendar.getInstance()
        Log.d("nemo dateAndTimeCalendar resulting calendar 1", calendar.time.toStringYMDTHM())
        calendar.time = date
        calendarHM.time = time
        Log.d("nemo dateAndTimeCalendar resulting calendar 2", calendar.time.toStringYMDTHM())
        calendar.set(Calendar.HOUR_OF_DAY, calendarHM.get(Calendar.HOUR_OF_DAY))
        calendar.set(Calendar.MINUTE, calendarHM.get(Calendar.MINUTE))
        Log.d("nemo dateAndTimeCalendar date YMD", date.toStringYMD())
        Log.d("nemo dateAndTimeCalendar date YMDTHM", date.toStringYMDTHM())
        Log.d("nemo dateAndTimeCalendar time toStringHM", time.toStringHM())
        Log.d("nemo dateAndTimeCalendar time toStringYMDTHM", time.toStringYMDTHM())
        Log.d("nemo dateAndTimeCalendar resulting calendar 3", calendar.time.toStringYMDTHM())
        return calendar.time
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun Date.toStringYMDTHM(locale: Locale = Locale.getDefault()): String = toString("yyyy-MM-dd'T'HH:mm:ss", locale)
    fun Date.toStringYMD(locale: Locale = Locale.getDefault()): String = toString("yyyy/MM/dd", locale)
    fun Date.toStringHM(locale: Locale = Locale.getDefault()): String = toString("HH:mm", locale)

    fun convertToSendMindset(convertText: String) : String {
        return when(convertText) {
            "Reposé" -> "rested"
            "Heureux" -> "happy"
            "Fatigué" -> "tired"
            "En colère" -> "angry"
            else -> ""
        }
    }
    fun convertToShowMindset(convertText: String) : String {
        return when(convertText) {
            "rested" -> "Reposé"
            "happy" -> "Heureux"
            "tired" -> "Fatigué"
            "angry" -> "En colère"
            else -> ""
        }
    }

    /*  return true if invalid */
    inline fun <reified String, V> HashMap<String, V>.isInvalidJSON(): Boolean {
        this.values.forEach {
            if (it is String) {
                if ((it as String).toString().isBlank()) {
                    return true
                }
            } else if (it == null) {
                return true
            }
        }
        return false
    }

    fun fitHabAlcoholForPreview() = Alcohol(
        idAlcohol = "aaa-aaa-aaa",
        alcoholName = "Corona Extra",
        amount = 335,
        unit = "ml",
        format = "can",
        percentAlcohol = 4.6f,
        calories = 148,
        protein = 1.2f,
        carbs = 13.9f,
        fiber = 0f,
        fat = 0f
    )

    fun fitHabActivityForPreview() = Activity(
        idActivity = "aaa-aaa-aaa",
        activityName = "Swimming",
    )

    fun fitHabFoodForPreview() = Food(
        idFood = "aaa-aaa-aaa",
        foodName = "Viande froide, poitrine de poulet",
        unit = "g",
        amount = 56,
        calories = 148,
        protein = 8f,
        carbs = 1f,
        sugar = 4f,
        fiber = 1.4f,
        totalFat = 1.6f,
        saturatedFat = 0.8f
    )

    fun fitHabUiStateForPreview() = FitHabUiState(userInfo = userInfoForPreview(), moduleUiStates = emptyList())

    fun userInfoForPreview() = UserInfo(
        user = User(
            email = "test@test.com",
            pseudo = "test",
            firstName = "testfirst",
            lastName = "testlast",
            sex = "Male",
            birthday = Date(Date().time),
            size = 1.6f,
            sizeType = "m",
            weight = 60f,
            weightType = "kg",
            liquidType = "ml",
            createdAt = Date(Date().time)
        ),
        userAlcohols =  mutableStateOf(
            listOf(
                UserAlcohol(
                    idUserAlcohol = "111-111-111",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 2,
                    timestamp = Date()
                ),
                UserAlcohol(
                    idUserAlcohol = "222-222-222",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 5,
                    timestamp = Date().removeXDay(1)
                ),
                UserAlcohol(
                    idUserAlcohol = "333-333-333",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 1,
                    timestamp = Date().removeXDay(2)
                ),
                UserAlcohol(
                    idUserAlcohol = "111-111-111",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 2,
                    timestamp = Date(Date().time)
                ),
                UserAlcohol(
                    idUserAlcohol = "222-222-222",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 5,
                    timestamp = Date().removeXDay(2)
                ),
                UserAlcohol(
                    idUserAlcohol = "333-333-333",
                    alcohol = fitHabAlcoholForPreview(),
                    numberOfConsumption = 1,
                    timestamp = Date().removeXDay(3)
                )
            )
        ),
        userFoods = mutableStateOf(
            listOf(
                UserFood(
                    idUserFood = "111-111-111",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 2,
                    timestamp = Date()
                ),
                UserFood(
                    idUserFood = "222-222-222",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 5,
                    timestamp = Date().removeXDay(1)
                ),
                UserFood(
                    idUserFood = "333-333-333",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 1,
                    timestamp = Date().removeXDay(2)
                ),
                UserFood(
                    idUserFood = "111-111-111",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 2,
                    timestamp = Date().removeXDay(2)
                ),
                UserFood(
                    idUserFood = "222-222-222",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 5,
                    timestamp = Date().removeXDay(1)
                ),
                UserFood(
                    idUserFood = "333-333-333",
                    food = fitHabFoodForPreview(),
                    numberOfConsumption = 1,
                    timestamp = Date().removeXDay(3)
                )
            )
        ),
        userWeight =  mutableStateOf(
            listOf(
                UserWeight(
                    idUserWeight = "111-111-111",
                    weight = 150,
                    preferenceUnit = "lbs",
                    timestamp = Date()
                ),
                UserWeight(
                    idUserWeight = "111-111-112",
                    weight = 155,
                    preferenceUnit = "lbs",
                    timestamp = Date().removeXDay(1)
                ),
                UserWeight(
                    idUserWeight = "111-111-113",
                    weight = 160,
                    preferenceUnit = "lbs",
                    timestamp = Date().removeXDay(2)
                ),
                UserWeight(
                    idUserWeight = "111-111-114",
                    weight = 165,
                    preferenceUnit = "lbs",
                    timestamp = Date().removeXDay(3)
                )
            )
        ),
        userSleep =  mutableStateOf(
            listOf(
                UserSleep(
                    idUserSleep = "111-111-111",
                    startSleep = Date().removeXDay(1),
                    endSleep = Date(),
                    numberOfAwakening = 2,
                    mindset= "rested",
                    totalSleepTime= 24,
                    timestamp = Date()
                ),
                UserSleep(
                    idUserSleep = "111-111-112",
                    startSleep = Date(Date().time),
                    endSleep = Date(Date().time),
                    numberOfAwakening = 3,
                    mindset= "rested",
                    totalSleepTime= 8,
                    timestamp = Date(Date().time)
                ),
                UserSleep(
                    idUserSleep = "111-111-113",
                    startSleep = Date(Date().time),
                    endSleep = Date(Date().time),
                    numberOfAwakening = 3,
                    mindset= "rested",
                    totalSleepTime= 6,
                    timestamp = Date(Date().time)
                ),
                UserSleep(
                    idUserSleep = "111-111-114",
                    startSleep = Date(Date().time),
                    endSleep = Date(Date().time),
                    numberOfAwakening = 2,
                    mindset= "rested",
                    totalSleepTime= 7,
                    timestamp = Date(Date().time)
                ),
            )
        ),
        userToilet =  mutableStateOf(
            listOf(
                UserToilet(
                    idUserToilet = "111-111-111",
                    timestamp = Date(),
                    fecesType = 1,
                    numberOfUrine = 1
                ),
                UserToilet(
                    idUserToilet = "111-111-112",
                    timestamp = Date().removeXDay(1),
                    fecesType = 1,
                    numberOfUrine = 2
                ),
                UserToilet(
                    idUserToilet = "111-111-113",
                    timestamp = Date().removeXDay(2),
                    fecesType = 1,
                    numberOfUrine = 5
                ),
                UserToilet(
                    idUserToilet = "111-111-114",
                    timestamp = Date().removeXDay(1),
                    fecesType = 0,
                    numberOfUrine = 3
                ),
            )
        ),
        userBloodSugar =  mutableStateOf(
            listOf(
                UserBloodSugar(
                    idUserBloodSugar = "111-111-111",
                    preferenceUnit = "mmol/L",
                    bloodSugar = 10.5,
                    timestamp = Date(Date().time)
                ),
                UserBloodSugar(
                    idUserBloodSugar = "111-111-112",
                    preferenceUnit = "mmol/L",
                    bloodSugar = 11.0,
                    timestamp = Date().removeXDay(1)
                ),
                UserBloodSugar(
                    idUserBloodSugar = "111-111-113",
                    preferenceUnit = "mmol/L",
                    bloodSugar = 9.0,
                    timestamp = Date().removeXDay(2)
                ),
                UserBloodSugar(
                    idUserBloodSugar = "111-111-114",
                    preferenceUnit = "mmol/L",
                    bloodSugar = 12.2,
                    timestamp = Date().removeXDay(3)
                ),
            )
        ),
        userActivity =  mutableStateOf(
            listOf(
                UserActivity(
                    idUserActivity = "111-111-111",
                    activity = fitHabActivityForPreview(),
                    activityIntensity = FitHabData.fitHabConst.ActivityIntensityEnum[0],
                    activityDuration = 1,
                    timestamp = Date()
                ),
                UserActivity(
                    idUserActivity = "111-111-112",
                    activity = fitHabActivityForPreview(),
                    activityIntensity = FitHabData.fitHabConst.ActivityIntensityEnum[1],
                    activityDuration = 2,
                    timestamp = Date().removeXDay(1)
                ),
                UserActivity(
                    idUserActivity = "111-111-113",
                    activity = fitHabActivityForPreview(),
                    activityIntensity = FitHabData.fitHabConst.ActivityIntensityEnum[2],
                    activityDuration = 3,
                    timestamp = Date().removeXDay(2)
                ),
                UserActivity(
                    idUserActivity = "111-111-114",
                    activity = fitHabActivityForPreview(),
                    activityIntensity = FitHabData.fitHabConst.ActivityIntensityEnum[3],
                    activityDuration = 4,
                    timestamp = Date().removeXDay(3)
                ),
            )
        ),
    )
}