package com.example.fitnessandroid.api.restrequest.food

import android.util.Log
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.homescreen.food.Food
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIFood {
    private val API : APIWrapper = APIWrapper()

    fun createFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été créé")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteFood(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "food",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteFood", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getAllFoodByIdUser(json : HashMap<String,String>, then: (List<Food>) -> Unit){
        API.requestWrapper(
            "food/all",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getAllFoodByIdUser", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Food>>() {}.type
                val foods = gson.fromJson<List<Food>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(foods)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getFoodsByName(json : HashMap<String,String>, then: (List<Food>) -> Unit){
        API.requestWrapper(
            "food",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getFoodsByName", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<Food>>() {}.type
                val foods = gson.fromJson<List<Food>>(gson.toJsonTree(res.data).asJsonArray, itemType)
                then(foods)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}