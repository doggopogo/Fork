package com.example.fitnessandroid.api.restrequest.alcohol

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.alcohol.Alcohol
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIAlcohol {
    private val API : APIWrapper = APIWrapper()

    fun createAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été créé")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteAlcohol(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "alcohols",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteAlcohol", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getAllAlcoholByIdUser(json : HashMap<String,String>, then: (List<Alcohol>) -> Unit){
        API.requestWrapper(
            "alcohols/all",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getAllAlcoholByIdUser", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Alcohol>>() {}.type
                val alcohols = gson.fromJson<List<Alcohol>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(alcohols)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getAlcoholsByName(json : HashMap<String,String>, then: (List<Alcohol>) -> Unit){
        API.requestWrapper(
            "alcohols",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getAlcoholsByName", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Alcohol>>() {}.type
                val alcohols = gson.fromJson<List<Alcohol>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(alcohols)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}