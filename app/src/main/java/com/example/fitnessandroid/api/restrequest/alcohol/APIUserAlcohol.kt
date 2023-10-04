package com.example.fitnessandroid.api.restrequest.alcohol

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIENV
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.alcohol.user.UserAlcohol
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserAlcohol {
    private val API : APIWrapper = APIWrapper()

    fun createUserAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été retiré")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserAlcohol(json : HashMap<String,String>, then: (List<UserAlcohol>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "alcohols/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserAlcohol", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserAlcohol>>() {}.type
                val userAlcohols = gson.fromJson<List<UserAlcohol>>(gson.toJsonTree(res.data).asJsonObject.get("alcohols").asJsonArray, itemType)
                then(userAlcohols)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserAlcoholStats(json : HashMap<String,String>, then: (UserAlcohol) -> Unit){
        API.requestWrapper(
            "alcohols/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserAlcoholStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userAlcohol = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserAlcohol::class.java)
                then(userAlcohol)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserAlcoholTarget(json : HashMap<String,String>, then: (UserAlcohol) -> Unit){
        API.requestWrapper(
            "alcohols/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserAlcoholTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userAlcohol = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserAlcohol::class.java)
                then(userAlcohol)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserAlcoholTarget(json : HashMap<String,String>, then: (UserAlcohol) -> Unit){
        API.requestWrapper(
            "alcohols/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserAlcoholTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userAlcohol = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserAlcohol::class.java)
                then(userAlcohol)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}