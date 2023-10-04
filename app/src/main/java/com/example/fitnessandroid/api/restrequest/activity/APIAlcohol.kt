package com.example.fitnessandroid.api.restrequest.activity

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.activity.Activity
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIActivity {
    private val API : APIWrapper = APIWrapper()

    fun createActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été créé")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteActivity(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "activity",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteActivity", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getAllActivityByIdUser(json : HashMap<String,String>, then: (List<Activity>) -> Unit){
        API.requestWrapper(
            "activity/all",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getAllActivityByIdUser", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Activity>>() {}.type
                val activities = gson.fromJson<List<Activity>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(activities)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getActivityByName(json : HashMap<String,String>, then: (List<Activity>) -> Unit){
        API.requestWrapper(
            "activity",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getActivityByName", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Activity>>() {}.type
                val activities = gson.fromJson<List<Activity>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(activities)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}