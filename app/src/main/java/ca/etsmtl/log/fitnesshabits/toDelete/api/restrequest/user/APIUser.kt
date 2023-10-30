package ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.user

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import ca.etsmtl.log.fitnesshabits.helper.Utilities.getHashMapMultiplesValues
import ca.etsmtl.log.fitnesshabits.helper.Utilities.getHashMapOneValue
import ca.etsmtl.log.fitnesshabits.toDelete.api.model.OperationResult
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIWrapper
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.HTTPMETHOD
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabConst
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.user.User
import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer.DrawerActivity
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
                    val intent = Intent(context, ca.etsmtl.log.fitnesshabits.MainActivity::class.java)
                    startActivity(context, intent,null)
                }
            } else {
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
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
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
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
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
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
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
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
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
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
                ca.etsmtl.log.fitnesshabits.MainActivity.showErrorMessage(res.message)
            }
        }
    }

}