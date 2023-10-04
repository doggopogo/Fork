package com.example.fitnessandroid.api.restrequest.activity

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIENV
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.activity.user.UserActivity
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserActivity {
    private val API : APIWrapper = APIWrapper()

    fun createUserActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été retiré")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserActivity(json : HashMap<String,String>, then: (List<UserActivity>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "activity/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserActivity", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserActivity>>() {}.type
                val userActivities = gson.fromJson<List<UserActivity>>(gson.toJsonTree(res.data).asJsonObject.get("activities").asJsonArray, itemType)
                then(userActivities)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserActivityStats(json : HashMap<String,String>, then: (UserActivity) -> Unit){
        API.requestWrapper(
            "activity/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserActivityStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userActivities = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserActivity::class.java)
                then(userActivities)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserActivityTarget(json : HashMap<String,String>, then: (UserActivity) -> Unit){
        API.requestWrapper(
            "activity/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserActivityTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userActivities = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserActivity::class.java)
                then(userActivities)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserActivityTarget(json : HashMap<String,String>, then: (UserActivity) -> Unit){
        API.requestWrapper(
            "activity/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserActivityTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userActivities = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserActivity::class.java)
                then(userActivities)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}