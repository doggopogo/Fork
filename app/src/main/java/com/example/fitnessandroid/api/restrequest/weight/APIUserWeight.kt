package com.example.fitnessandroid.api.restrequest.weight

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIENV
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.weight.user.UserWeight
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserWeight {
    private val API : APIWrapper = APIWrapper()

    fun createUserWeight(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "weights/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserWeight", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserWeight(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "weights/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserWeight", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserWeight(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "weights/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserWeight", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserWeight(json : HashMap<String,String>, then: (List<UserWeight>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "weights/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserWeight", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserWeight>>() {}.type
                val userWeight = gson.fromJson<List<UserWeight>>(gson.toJsonTree(res.data).asJsonObject.get("weights").asJsonArray, itemType)
                then(userWeight)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserWeightStats(json : HashMap<String,String>, then: (UserWeight) -> Unit){
        API.requestWrapper(
            "weights/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserWeightStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userWeight = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserWeight::class.java)
                then(userWeight)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserWeightTarget(json : HashMap<String,String>, then: (UserWeight) -> Unit){
        API.requestWrapper(
            "weights/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserWeightTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userWeight = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserWeight::class.java)
                then(userWeight)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserWeightTarget(json : HashMap<String,String>, then: (UserWeight) -> Unit){
        API.requestWrapper(
            "weights/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserWeightTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userWeight = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserWeight::class.java)
                then(userWeight)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}