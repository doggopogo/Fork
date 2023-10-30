package ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.toilet

import android.util.Log
import ca.etsmtl.log.fitnesshabits.toDelete.api.model.OperationResult
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIENV
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIWrapper
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.HTTPMETHOD
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.toilet.user.UserToilet
import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserToilet {
    private val API : APIWrapper = APIWrapper()

    fun createUserToilet(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "toilets/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserToilet", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserToilet(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "toilets/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserToilet", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserToilet(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "toilets/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserToilet", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été détruit")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserToilet(json : HashMap<String,String>, then: (List<UserToilet>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "toilets/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserToilet", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserToilet>>() {}.type
                val userToilet = gson.fromJson<List<UserToilet>>(gson.toJsonTree(res.data).asJsonObject.get("toilets").asJsonArray, itemType)
                then(userToilet)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserToiletStats(json : HashMap<String,String>, then: (UserToilet) -> Unit){
        API.requestWrapper(
            "toilets/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserToiletStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userToilet = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserToilet::class.java)
                then(userToilet)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserToiletTarget(json : HashMap<String,String>, then: (UserToilet) -> Unit){
        API.requestWrapper(
            "toilets/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserToiletTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userToilet = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserToilet::class.java)
                then(userToilet)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserToiletTarget(json : HashMap<String,String>, then: (UserToilet) -> Unit){
        API.requestWrapper(
            "toilets/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserToiletTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userToilet = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserToilet::class.java)
                then(userToilet)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}