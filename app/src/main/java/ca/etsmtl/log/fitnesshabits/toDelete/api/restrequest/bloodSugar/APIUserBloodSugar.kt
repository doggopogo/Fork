package ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.bloodSugar

import android.util.Log
import ca.etsmtl.log.fitnesshabits.toDelete.api.model.OperationResult
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIENV
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.APIWrapper
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.HTTPMETHOD
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.bloodSugar.user.UserBloodSugar
import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer.DrawerActivity
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object APIUserBloodSugar {
    private val API : APIWrapper = APIWrapper()

    fun createUserBloodSugar(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "bloodSugars/user",
            HTTPMETHOD.POST,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserBloodSugar", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été ajouté")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun updateUserBloodSugar(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "bloodSugars/user",
            HTTPMETHOD.PUT,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for updateUserBloodSugar", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été mis à jour")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun deleteUserBloodSugar(json : HashMap<String,String>, then: () -> Unit){
        API.requestWrapper(
            "bloodSugars/user",
            HTTPMETHOD.DELETE,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for deleteUserBloodSugar", res.toString())
            if (res.statusCode == 200) {
                DrawerActivity.showErrorMessage("L'entrée a été retiré")
                then()
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserBloodSugar(json : HashMap<String,String>, then: (List<UserBloodSugar>) -> Unit){
        json["periodOfTime"] = APIENV.PERIOD_OF_TIME
        API.requestWrapper(
            "bloodSugars/user",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserBloodSugar", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val itemType = object : TypeToken<List<UserBloodSugar>>() {}.type
                val userBloodSugar = gson.fromJson<List<UserBloodSugar>>(gson.toJsonTree(res.data).asJsonObject.get("bloodSugars").asJsonArray, itemType)
                then(userBloodSugar)
            } else {
                then(emptyList())
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserBloodSugarStats(json : HashMap<String,String>, then: (UserBloodSugar) -> Unit){
        API.requestWrapper(
            "bloodSugars/user/stats",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserBloodSugarStats", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userBloodSugar = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserBloodSugar::class.java)
                then(userBloodSugar)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun getUserBloodSugarTarget(json : HashMap<String,String>, then: (UserBloodSugar) -> Unit){
        API.requestWrapper(
            "bloodSugars/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for getUserBloodSugarTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userBloodSugar = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserBloodSugar::class.java)
                then(userBloodSugar)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

    fun createUserBloodSugarTarget(json : HashMap<String,String>, then: (UserBloodSugar) -> Unit){
        API.requestWrapper(
            "bloodSugars/user/target",
            HTTPMETHOD.GET,
            json
        ) { res: OperationResult ->
            Log.d("nemo then function for createUserBloodSugarTarget", res.toString())
            if (res.statusCode == 200) {
                val gson = GsonBuilder().setLenient().create()
                val userBloodSugar = gson.fromJson(gson.toJsonTree(res.data).asJsonObject, UserBloodSugar::class.java)
                then(userBloodSugar)
            } else {
                DrawerActivity.showErrorMessage(res.message)
            }
        }
    }

}