package com.example.fitnessandroid.fithabdata

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.example.fitnessandroid.R
import com.example.fitnessandroid.api.restrequest.activity.APIActivity
import com.example.fitnessandroid.api.restrequest.activity.APIUserActivity
import com.example.fitnessandroid.api.restrequest.alcohol.APIAlcohol
import com.example.fitnessandroid.api.restrequest.alcohol.APIUserAlcohol
import com.example.fitnessandroid.api.restrequest.bloodSugar.APIUserBloodSugar
import com.example.fitnessandroid.api.restrequest.food.APIFood
import com.example.fitnessandroid.api.restrequest.food.APIUserFood
import com.example.fitnessandroid.api.restrequest.sleep.APIUserSleep
import com.example.fitnessandroid.api.restrequest.toilet.APIUserToilet
import com.example.fitnessandroid.api.restrequest.user.APIUser
import com.example.fitnessandroid.api.restrequest.weight.APIUserWeight
import com.example.fitnessandroid.composable.SwitchItem
import com.example.fitnessandroid.homescreen.HomeModuleItem
import com.example.fitnessandroid.homescreen.ModuleUiState
import com.example.fitnessandroid.homescreen.activity.Activity
import com.example.fitnessandroid.homescreen.activity.user.UserActivity
import com.example.fitnessandroid.homescreen.activity.user.UserActivityModal
import com.example.fitnessandroid.homescreen.alcohol.Alcohol
import com.example.fitnessandroid.homescreen.alcohol.user.UserAlcohol
import com.example.fitnessandroid.homescreen.alcohol.user.UserAlcoholModal
import com.example.fitnessandroid.homescreen.bloodSugar.user.UserBloodSugar
import com.example.fitnessandroid.homescreen.bloodSugar.user.UserBloodSugarModal
import com.example.fitnessandroid.homescreen.food.Food
import com.example.fitnessandroid.homescreen.food.user.UserFood
import com.example.fitnessandroid.homescreen.food.user.UserFoodModal
import com.example.fitnessandroid.homescreen.sleep.user.UserSleep
import com.example.fitnessandroid.homescreen.sleep.user.UserSleepModal
import com.example.fitnessandroid.homescreen.toilet.user.UserToilet
import com.example.fitnessandroid.homescreen.toilet.user.UserToiletModal
import com.example.fitnessandroid.homescreen.user.User
import com.example.fitnessandroid.homescreen.weight.user.UserWeight
import com.example.fitnessandroid.homescreen.weight.user.UserWeightModal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FitHabUiState(
    var userInfo: UserInfo = UserInfo(),
    var moduleUiStates: List<ModuleUiState> = createDefaultModuleUiState(),
    var modules: MutableState<HashMap<String, Boolean>> = mutableStateOf(hashMapOf()),
    var alcohols: MutableState<List<Alcohol>> = mutableStateOf(emptyList()),
    var createdAlcohols: MutableState<List<Alcohol>> = mutableStateOf(emptyList()),
    var foods: MutableState<List<Food>> = mutableStateOf(emptyList()),
    var createdFood: MutableState<List<Food>> = mutableStateOf(emptyList()),
    var activities: MutableState<List<Activity>> = mutableStateOf(emptyList()),
    var createdActivities: MutableState<List<Activity>> = mutableStateOf(emptyList()),
)

data class UserInfo(
    var user : User? = null,
    var userAlcohols : MutableState<List<UserAlcohol>> = mutableStateOf(emptyList()),
    var userFoods : MutableState<List<UserFood>> = mutableStateOf(emptyList()),
    var userWeight : MutableState<List<UserWeight>> = mutableStateOf(emptyList()),
    var userSleep : MutableState<List<UserSleep>> = mutableStateOf(emptyList()),
    var userToilet : MutableState<List<UserToilet>> = mutableStateOf(emptyList()),
    var userBloodSugar : MutableState<List<UserBloodSugar>> = mutableStateOf(emptyList()),
    var userActivity : MutableState<List<UserActivity>> = mutableStateOf(emptyList()),
)

object FitHabData {
    private var _uiState : MutableStateFlow<FitHabUiState> = MutableStateFlow(FitHabUiState())
    val uiState: StateFlow<FitHabUiState> = _uiState.asStateFlow()

    var fitHabConst = FitHabConst()

    var idUser : String = ""
        private set

    fun setUser(id: String, user : User) {
        idUser = id
        _uiState.value.userInfo.user = user
        updateAllModules()
    }

    /*  Alcohols START  */

    fun getUserAlcohols() {
        Log.d("nemo fun","getUserAlcohol")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserAlcohol.getUserAlcohol(data) {
            _uiState.value.userInfo.userAlcohols.value = it
        }
    }

    fun deleteUserAlcohol(idUserAlcohol : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserAlcohol", idUserAlcohol)
        )
        Log.d("nemo fun","deleteUserAlcohol")
        APIUserAlcohol.deleteUserAlcohol(json) {
            getUserAlcohols()
            Log.d("nemo fun","getUserAlcohol in deleteUserAlcohol done")
        }
    }

    fun getAlcohols(json : HashMap<String,String> = hashMapOf(Pair("alcoholName", ""))) {
        Log.d("nemo fun","getAlcohols")
        APIAlcohol.getAlcoholsByName(json) {
            _uiState.value.alcohols.value = it
        }
    }

    fun getCreatedByAlcohols() {
        Log.d("nemo fun","getCreatedByAlcohols")
        val json : HashMap<String,String> = hashMapOf(
            Pair("createdBy", idUser),
        )
        APIAlcohol.getAllAlcoholByIdUser(json) {
            _uiState.value.createdAlcohols.value = it
        }
    }

    fun createUserAlcohol(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserAlcohol")
        APIUserAlcohol.createUserAlcohol(json) {
            getUserAlcohols()
            Log.d("nemo fun","getUserAlcohols in createUserAlcohol done")
        }
    }


    fun updateUserAlcohol(json : HashMap<String,String>) {
        Log.d("nemo fun","updateUserAlcohol")
        json["idUser"] = idUser
        APIUserAlcohol.updateUserAlcohol(json) {
            getUserAlcohols()
            Log.d("nemo fun","getUserAlcohols in updateUserAlcohol done")
        }
    }

    fun createAlcohol(json : HashMap<String,String>) {
        Log.d("nemo fun","createAlcohol")
        json["createdBy"] = idUser
        APIAlcohol.createAlcohol(json) {
            getAlcohols()
            Log.d("nemo fun","getAlcohols in createAlcohol done")
        }
    }

    fun updateAlcohol(json : HashMap<String,String>) {
        Log.d("nemo fun","updateAlcohol")
        json["createdBy"] = idUser
        APIAlcohol.updateAlcohol(json) {
            getCreatedByAlcohols()
            Log.d("nemo fun","getAlcohols in updateAlcohol done")
        }
    }

    fun deleteAlcohol(json : HashMap<String,String>) {
        Log.d("nemo fun","deleteAlcohol")
        json["createdBy"] = idUser
        APIAlcohol.deleteAlcohol(json) {
            getCreatedByAlcohols()
            Log.d("nemo fun","getAlcohols in deleteAlcohol done")
        }
    }

    /*  Alcohols END  */

    /*  Foods START  */

    fun getUserFoods() {
        Log.d("nemo fun","getUserFoods")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserFood.getUserFood(data) {
            _uiState.value.userInfo.userFoods.value = it
        }
    }

    fun deleteUserFood(idUserFood : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserFood", idUserFood)
        )
        Log.d("nemo fun","deleteUserFood")
        APIUserFood.deleteUserFood(json) {
            getUserFoods()
            Log.d("nemo fun","getUserFoods in deleteUserFood done")
        }
    }

    fun getFoods(json : HashMap<String,String> = hashMapOf(Pair("foodName", ""))) {
        Log.d("nemo fun","getFoods")
        APIFood.getFoodsByName(json) {
            _uiState.value.foods.value = it
        }
    }

    fun getCreatedByFoods() {
        Log.d("nemo fun","getCreatedByFoods")
        val json : HashMap<String,String> = hashMapOf(
            Pair("createdBy", idUser),
        )
        APIFood.getAllFoodByIdUser(json) {
            _uiState.value.createdFood.value = it
        }
    }

    fun createUserFood(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserFood")
        APIUserFood.createUserFood(json) {
            getUserFoods()
            Log.d("nemo fun","getUserFoods in createUserFood done")
        }
    }

    fun updateUserFood(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","updateUserFood")
        APIUserFood.updateUserFood(json) {
            getUserFoods()
            Log.d("nemo fun","getUserFoods in updateUserFood done")
        }
    }

    fun createFood(json : HashMap<String,String>) {
        Log.d("nemo fun","createFood")
        json["createdBy"] = idUser
        APIFood.createFood(json) {
            getFoods()
            Log.d("nemo fun","getAlcohols in createFood done")
        }
    }

    fun updateFood(json : HashMap<String,String>) {
        Log.d("nemo fun","updateFood")
        json["createdBy"] = idUser
        APIFood.updateFood(json) {
            getCreatedByFoods()
            Log.d("nemo fun","getCreatedByFoods in updateFood done")
        }
    }

    fun deleteFood(json : HashMap<String,String>) {
        Log.d("nemo fun","deleteFood")
        json["createdBy"] = idUser
        APIFood.deleteFood(json) {
            getCreatedByFoods()
            Log.d("nemo fun","getCreatedByFoods in deleteFood done")
        }
    }

    /*  Food END  */

    /*  Activity START  */

    fun getUserActivity() {
        Log.d("nemo fun","getUserActivity")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserActivity.getUserActivity(data) {
            _uiState.value.userInfo.userActivity.value = it
        }
    }

    fun deleteUserActivity(idUserActivity : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserActivity", idUserActivity)
        )
        Log.d("nemo fun","deleteUserActivity")
        APIUserActivity.deleteUserActivity(json) {
            getUserActivity()
            Log.d("nemo fun","getUserActivity in deleteUserActivity done")
        }
    }

    fun getActivities(json : HashMap<String,String> = hashMapOf(Pair("activityName", ""))) {
        Log.d("nemo fun","getActivities")
        APIActivity.getActivityByName(json) {
            _uiState.value.activities.value = it
        }
    }

    fun getCreatedByActivities() {
        Log.d("nemo fun","getCreatedByActivities")
        val json : HashMap<String,String> = hashMapOf(
            Pair("createdBy", idUser),
        )
        APIActivity.getAllActivityByIdUser(json) {
            _uiState.value.createdActivities.value = it
        }
    }

    fun createUserActivity(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserActivity")
        APIUserActivity.createUserActivity(json) {
            getUserActivity()
            Log.d("nemo fun","getUserActivity in createUserActivity done")
        }
    }

    fun updateUserActivity(json : HashMap<String,String>) {
        Log.d("nemo fun","updateActivity")
        json["idUser"] = idUser
        APIUserActivity.updateUserActivity(json) {
            getUserActivity()
            Log.d("nemo fun","getUserActivity in updateUserActivity done")
        }
    }

    fun createActivity(json : HashMap<String,String>) {
        Log.d("nemo fun","createActivity")
        json["createdBy"] = idUser
        APIActivity.createActivity(json) {
            getAlcohols()
            Log.d("nemo fun","createActivity in createActivity done")
        }
    }

    fun updateActivity(json : HashMap<String,String>) {
        Log.d("nemo fun","updateActivity")
        json["createdBy"] = idUser
        APIActivity.updateActivity(json) {
            getCreatedByActivities()
            Log.d("nemo fun","getCreatedByActivities in updateActivity done")
        }
    }

    fun deleteActivity(json : HashMap<String,String>) {
        Log.d("nemo fun","deleteActivity")
        json["createdBy"] = idUser
        APIActivity.deleteActivity(json) {
            getCreatedByActivities()
            Log.d("nemo fun","getCreatedByActivities in deleteActivity done")
        }
    }

    /*  Activity END  */

    fun createUserWeight(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun", "createUserWeight")
        APIUserWeight.createUserWeight(json) {
            getUserWeight()
            Log.d("nemo fun", "getUserWeight in createUserWeight done")
        }
    }

    fun updateUserWeight(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","updateUserWeight")
        APIUserWeight.updateUserWeight(json) {
            getUserWeight()
            Log.d("nemo fun","getUserWeight in updateUserWeight done")
        }
    }

    fun getUserWeight() {
        Log.d("nemo fun","getUserWeight")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserWeight.getUserWeight(data) {
            _uiState.value.userInfo.userWeight.value = it
        }
    }

    fun deleteUserWeight(idUserWeight : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserWeight", idUserWeight)
        )
        Log.d("nemo fun","deleteUserWeight")
        APIUserWeight.deleteUserWeight(json) {
            getUserWeight()
            Log.d("nemo fun","getUserWeight in deleteUserWeight done")
        }
    }

    fun getUserSleep() {
        Log.d("nemo fun","getUserSleep")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserSleep.getUserSleep(data) {
            _uiState.value.userInfo.userSleep.value = it
        }
    }

    fun createUserSleep(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserSleep")
        APIUserSleep.createUserSleep(json) {
            getUserSleep()
            Log.d("nemo fun","getUserSleep in createUserSleep done")
        }
    }

    fun updateUserSleep(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","updateUserSleep")
        APIUserSleep.updateUserSleep(json) {
            getUserSleep()
            Log.d("nemo fun","getUserSleep in updateUserSleep done")
        }
    }

    fun deleteUserSleep(idUserSleep : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserSleep", idUserSleep)
        )
        Log.d("nemo fun","deleteUserSleep")
        APIUserSleep.deleteUserSleep(json) {
            getUserSleep()
            Log.d("nemo fun","getUserSleep in deleteUserSleep done")
        }
    }

    fun getUserToilet() {
        Log.d("nemo fun","getUserToilet")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserToilet.getUserToilet(data) {
            _uiState.value.userInfo.userToilet.value = it
        }
    }

    fun createUserToilet(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserSleep")
        APIUserToilet.createUserToilet(json) {
            getUserToilet()
            Log.d("nemo fun","getUserToilet in createUserSleep done")
        }
    }

    fun updateUserToilet(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","updateUserToilet")
        APIUserToilet.updateUserToilet(json) {
            getUserToilet()
            Log.d("nemo fun","getUserToilet in updateUserToilet done")
        }
    }

    fun deleteUserToilet(idUserToilet : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserToilet", idUserToilet)
        )
        Log.d("nemo fun","deleteUserToilet")
        APIUserToilet.deleteUserToilet(json) {
            getUserToilet()
            Log.d("nemo fun","getUserToilet in deleteUserToilet done")
        }
    }

    fun getUserBloodSugar() {
        Log.d("nemo fun","getUserBloodSugar")
        val data = hashMapOf(Pair("idUser", idUser))
        APIUserBloodSugar.getUserBloodSugar(data) {
            _uiState.value.userInfo.userBloodSugar.value = it
        }
    }

    fun createUserBloodSugar(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","createUserSleep")
        APIUserBloodSugar.createUserBloodSugar(json) {
            getUserBloodSugar()
            Log.d("nemo fun","getUserBloodSugar in createUserBloodSugar done")
        }
    }

    fun updateUserBloodSugar(json : HashMap<String,String>) {
        json["idUser"] = idUser
        Log.d("nemo fun","updateUserBloodSugar")
        APIUserBloodSugar.updateUserBloodSugar(json) {
            getUserBloodSugar()
            Log.d("nemo fun","getUserBloodSugar in updateUserBloodSugar done")
        }
    }

    fun deleteUserBloodSugar(idUserBloodSugar : String) {
        val json : HashMap<String,String> = hashMapOf(
            Pair("idUser", idUser),
            Pair("idUserBloodSugar", idUserBloodSugar)
        )
        Log.d("nemo fun","deleteUserBloodSugar")
        APIUserBloodSugar.deleteUserBloodSugar(json) {
            getUserBloodSugar()
            Log.d("nemo fun","getUserBloodSugar in deleteUserBloodSugar done")
        }
    }

    fun setUp() {
        getAllFitHabConst()
    }

    private fun getAllFitHabConst() {
        APIUser.getConst {
            fitHabConst = it
        }
    }


    fun setToggleModule(modules: HashMap<String,String>) {
        modules["idUser"] = idUser
        APIUser.updateUserToggle(modules) {
            updateAllModules()
        }
    }

    fun getTogglesModule() : List<SwitchItem> {
        val items : List<SwitchItem?> = _uiState.value.modules.value.map {
            when(it.key) {
                "food" -> SwitchItem(
                    imageId = R.drawable.icon_food,
                    text = "Food",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "sleep" -> SwitchItem(
                    imageId = R.drawable.icon_sleep,
                    text = "Sleep",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "activity" -> SwitchItem(
                    imageId = R.drawable.icon_activity,
                    text = "Activity",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "weight" -> SwitchItem(
                    imageId = R.drawable.icon_weight,
                    text = "Weight",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "alcohol" -> SwitchItem(
                    imageId = R.drawable.icon_alcohol,
                    text = "Alcohol",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "bloodsugar" -> SwitchItem(
                    imageId = R.drawable.icon_bloodsugar,
                    text = "Blood Sugar",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                "toilet" -> SwitchItem(
                    imageId = R.drawable.icon_toilet,
                    text = "Toilet",
                    toggleValue = it.key,
                    isChecked = it.value
                )
                else -> null
            }
        }
        return items.filterNotNull()
    }

    fun updateAllModules() {
        APIUser.getUserToggle(hashMapOf(Pair("idUser", idUser))) {
            _uiState.value.modules.value = it
            _uiState.value.moduleUiStates = createModuleListFromAPI(it)
        }
    }

    internal fun createModuleListFromAPI(moduleNames : HashMap<String, Boolean>) : List<ModuleUiState> {
        val moduleUiStates = mutableListOf<ModuleUiState>()
        moduleNames.forEach { (name, toShow) ->
            if (toShow) {
                createModule(name)?.let { moduleUiStates.add(it) }
            }
        }

        return moduleUiStates
    }

    private fun createModule(name : String) : ModuleUiState? {
        return when(name) {
            "food" -> ModuleUiState(
                icon = R.drawable.icon_food_white, iconModal = R.drawable.icon_food, backgroundColorIcon = R.color.food, title = "Nourriture",
                clickOnIconAction = {
                    getFoods()
                    UserFoodModal()
                },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Food.route)}
            )
            //"hydration" -> ModuleUiState(R.drawable.icon_hydration_white, R.color.hydration, name)
            //"supplements" -> ModuleUiState(R.drawable.icon_supplements_white, R.color.supplements, name)
            "sleep" -> ModuleUiState(
                icon = R.drawable.icon_sleep_white, iconModal = R.drawable.icon_sleep, backgroundColorIcon = R.color.sleep, title = "Sommeil",
                clickOnIconAction = { UserSleepModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Sleep.route)})
            "toilet" -> ModuleUiState(
                icon = R.drawable.icon_toilet_white, iconModal = R.drawable.icon_toilet, backgroundColorIcon = R.color.toilet, title = "Toilette",
                clickOnIconAction = { UserToiletModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Toilet.route)})
            "activity" -> ModuleUiState(
                icon = R.drawable.icon_activity_white, iconModal = R.drawable.icon_activity, backgroundColorIcon = R.color.activity, title = "Sports",
                clickOnIconAction = {
                    getActivities()
                    UserActivityModal()
                },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Activity.route)})
            "weight" -> ModuleUiState(
                icon = R.drawable.icon_weight_white, iconModal = R.drawable.icon_weight, backgroundColorIcon = R.color.weight, title = "Poids",
                clickOnIconAction = { UserWeightModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Weight.route)})
            "alcohol" -> ModuleUiState(
                icon = R.drawable.icon_alcohol_white, iconModal = R.drawable.icon_alcohol, backgroundColorIcon = R.color.alcohol, title = "Alcool",
                clickOnIconAction = {
                    getAlcohols()
                    UserAlcoholModal()
                },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Alcohol.route)}
            )
            "bloodsugar" -> ModuleUiState(
                icon = R.drawable.icon_bloodsugar_white, iconModal = R.drawable.icon_bloodsugar, backgroundColorIcon = R.color.bloodSugar, title = "Glycémie",
                clickOnIconAction = { UserBloodSugarModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.BloodSugar.route)}
            )
            else -> null
        }
    }

}

private fun createDefaultModuleUiState() : List<ModuleUiState> {
    return FitHabData.createModuleListFromAPI(
        hashMapOf(
            Pair("food", true),
            Pair("hydration", true),
            Pair("supplements", true),
            Pair("sleep", true),
            Pair("toilet", true),
            Pair("activity", true),
            Pair("weight", true),
            Pair("alcohol", true),
            Pair("bloodSugar", true),
        )
    )
}

fun FitHabUiState.isUserAuth() = this.userInfo.user != null