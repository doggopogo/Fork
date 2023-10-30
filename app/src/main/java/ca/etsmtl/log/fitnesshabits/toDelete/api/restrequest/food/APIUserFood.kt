package ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.food

import android.util.Log
import ca.etsmtl.log.fitnesshabits.toDelete.api.model.OperationResult
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIENV
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIWrapper
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.HTTPMETHOD
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.user.UserFood
import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserFood {
    private val API : APIWrapper = APIWrapper()

    fun createUserFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserFood(json : HashMap<String,String>, then: (List<UserFood>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "food/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserFood", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserFood>>() {}.type
                val userFood = gson.fromJson<List<UserFood>>(gson.toJsonTree(res.data).asJsonObject.get("foods").asJsonArray, itemType)
                then(userFood)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserFoodStats(json : HashMap<String,String>, then: (UserFood) -> Unit){
        API.requestWrapper(
            "food/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserAlcoholStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userFood = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserFood::class.java)
                then(userFood)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserFoodTarget(json : HashMap<String,String>, then: (UserFood) -> Unit){
        API.requestWrapper(
            "food/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserAlcoholTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userFood = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserFood::class.java)
                then(userFood)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserFoodTarget(json : HashMap<String,String>, then: (UserFood) -> Unit){
        API.requestWrapper(
            "food/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserAlcoholTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userFood = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserFood::class.java)
                then(userFood)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}