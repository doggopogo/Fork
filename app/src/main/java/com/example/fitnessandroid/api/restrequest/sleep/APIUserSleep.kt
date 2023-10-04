package com.example.fitnessandroid.api.restrequest.sleep

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIENV
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.sleep.user.UserSleep
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserSleep {
    private val API : APIWrapper = APIWrapper()

    fun createUserSleep(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "sleep/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserSleep", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserSleep(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "sleep/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserSleep", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserSleep(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "sleep/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserSleep", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserSleep(json : HashMap<String,String>, then: (List<UserSleep>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "sleep/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserSleep", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserSleep>>() {}.type
                val userSleep = gson.fromJson<List<UserSleep>>(gson.toJsonTree(res.data).asJsonObject.get("sleeps").asJsonArray, itemType)
                then(userSleep)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserSleepStats(json : HashMap<String,String>, then: (UserSleep) -> Unit){
        API.requestWrapper(
            "sleep/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserSleepStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userSleep = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserSleep::class.java)
                then(userSleep)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserSleepTarget(json : HashMap<String,String>, then: (UserSleep) -> Unit){
        API.requestWrapper(
            "sleep/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserSleepTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userSleep = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserSleep::class.java)
                then(userSleep)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserSleepTarget(json : HashMap<String,String>, then: (UserSleep) -> Unit){
        API.requestWrapper(
            "sleep/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserSleepTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userSleep = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserSleep::class.java)
                then(userSleep)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}