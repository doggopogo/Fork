package com.example.fitnessandroid.api.restrequest.user

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import com.example.fitnessandroid.MainActivity
import com.example.fitnessandroid.Utilities.getHashMapMultiplesValues
import com.example.fitnessandroid.Utilities.getHashMapOneValue
import com.example.fitnessandroid.api.model.OperationResult
import com.example.fitnessandroid.api.restrequest.APIWrapper
import com.example.fitnessandroid.api.restrequest.HTTPMETHOD
import com.example.fitnessandroid.fithabdata.FitHabConst
import com.example.fitnessandroid.homescreen.CustomAlert
import com.example.fitnessandroid.homescreen.ShowCustomAlert
import com.example.fitnessandroid.homescreen.user.User
import com.example.fitnessandroid.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder

object APIUser {
    private val API : APIWrapper = APIWrapper()

    fun login(context: Context, json : HashMap<String,*>){
        API.requestWrapper(
            "user/login",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for login", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val string = gson.toJson(res.data)
                Log.d("nemo res.data", string.toString())
                val data = string.getHashMapOneValue()
                Log.d("nemo data", data.toString())
                getUserInfo(data) {
                    Log.d("nemo then getUserInfo", it.toString())
                    val intent = Intent(context, DrawerActivity::class.java)
                    intent.putExtra("User", gson.toJson(it))
                    intent.putExtra("idUser", data["idUser"] as String)
                    startActivity(context, intent,null)
                }
            } else {
                //MainActivity.showErrorMessage(res.message)
            }
        }
        println("test-json=$json")
    }

    fun logout(context: Context?){
        API.requestWrapper(
            "user/logout",
            HTTPMETHOD.POST,
            hashMapOf<String,String>()
        ) { res: OperationResult ->
            Log.d("nemo then function for logout", res.toString())
            if (res.statusCode == 200) {
                context?.let {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(context, intent,null)
                }
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

    private fun getUserInfo(json : HashMap<String,*>, then : (User) -> Unit) {
        API.requestWrapper(
            "user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserInfo", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val data = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, User::class.java)
                then(data)
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

    fun register(context: Context, json : HashMap<String,*>) {
        API.requestWrapper(
            "user/register",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserInfo", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val string = gson.toJson(res.data)
                Log.d("nemo res.data", string.toString())
                val data = string.getHashMapOneValue()
                Log.d("nemo data", data.toString())
                getUserInfo(data) {
                    Log.d("nemo then getUserInfo", it.toString())
                    val intent = Intent(context, DrawerActivity::class.java)
                    intent.putExtra("User", gson.toJson(it))
                    intent.putExtra("idUser", data["idUser"] as String)
                    startActivity(context, intent,null)
                }
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getConst(then : (FitHabConst) -> Unit) {
        API.requestWrapper(
            "user/const",
            HTTPMETHOD.GET,
            hashMapOf<String, String>()
        ) { res: OperationResult ->
            Log.d("nemo then function for getConst", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val data = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, FitHabConst::class.java)
                then(data)
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserToggle(json : HashMap<String,*>, then : (HashMap<String, Boolean>) -> Unit) {
        API.requestWrapper(
            "user/toggle",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserToggle", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val string = gson.toJson(res.data)
                Log.d("nemo res.data", string.toString())
                val data = gson.toJsonTree(res.data).asJsonObject
                val hash : HashMap<String,Boolean> = hashMapOf()

                val hashmap = data.getAsJsonObject("toggles").toString().getHashMapMultiplesValues()
                hashmap.forEach { (key, value) ->
                    hash[key] = (value == "true")
                }

                then(hash)
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserToggle(json : HashMap<String,*>, then : () -> Unit) {
        API.requestWrapper(
            "user/toggle",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserToggle", res.toString())
            if (res.statusCode == 200) {
                then()
            } else {
                MainActivity.showErrorMessage(res.message)
            }
        }
    }

}