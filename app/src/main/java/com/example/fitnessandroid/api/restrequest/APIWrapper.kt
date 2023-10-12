package com.example.fitnessandroid.api.restrequest

import android.net.Uri
import android.util.Log
import com.example.fitnessandroid.api.model.HttpStatusCode
import com.example.fitnessandroid.api.model.OperationResult
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.net.CookieHandler
import java.net.CookieManager
import java.util.concurrent.TimeUnit

//127.0.0.1 serait la machine android, alors que je veux mon ordi
//pour aller son adresse, faire ipconfig dans un cmd
//const val BASE_URL = "http://s-fit.info.uqam.ca/api/"
//const val BASE_URL = "http://192.168.1.108:8080/api/"

// Jason's IP Address
//const val BASE_URL = "http://192.168.0.65:8080/api/"
//const val BASE_URL = "http://172.25.192.1:8080/api/"
const val BASE_URL = "http://192.168.4.49:8080/api/"
//const val BASE_URL = "http://172.29.96.8:8080/api/"
var cookieHandler: CookieHandler = CookieManager()

private val client : OkHttpClient
    private get() {
        val builder = OkHttpClient.Builder()
        builder.cookieJar(JavaNetCookieJar(cookieHandler))
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }

internal class APIWrapper {

    private fun createRequestBody(body : HashMap<String, *>) : RequestBody {
        val formBody = FormBody.Builder()

        for (i in body) {
            formBody.add(i.key, i.value as String)
            Log.d("nemo values to send", i.key + ": " + i.value)
        }

        return formBody.build()
    }

    fun requestWrapper(extension_url: String,method : String, json : HashMap<String, *>, then: ((OperationResult) -> Unit)) {
        Log.d("API CALL", "$BASE_URL$extension_url: $method")
        Log.d("API CALL DATA TO SEND", json.toString())
        if(method == HTTPMETHOD.GET) {
            getMethod(BASE_URL +extension_url, json, callbackMethod(then))
        } else {
            method(BASE_URL +extension_url, method, createRequestBody(json), callbackMethod(then))
        }
    }

    private fun callbackMethod(then: ((OperationResult) -> Unit)) : Callback {
        return object : Callback {
            override fun onResponse(call: Call, response: Response) {
                Log.d("nemo onResponse", "onResponse")
                val body = response.body?.string()
                if (body != null) {
                    Log.d("nemo body", body)
                }
                val gson = GsonBuilder().setLenient().create()
                val res = gson.fromJson(body, OperationResult::class.java)
                then(res)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.d("nemo onFailure", "onFailure")
                Log.d("nemo onFailure IOException", e.toString())
                val body = OperationResult(false, HttpStatusCode.STATUS_CODE_REQUEST_TIMEOUT, "Request Timeout")
                then(body)
            }
        }
    }

    private fun method(url: String, method: String, requestBody : RequestBody, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .method(method,requestBody)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    private fun getMethod(url: String, json : HashMap<String, *>, callback: Callback): Call {
        val builder: Uri.Builder = Uri.parse(url).buildUpon()
        for (entry in json) {
            builder.appendQueryParameter(entry.key, entry.value as String)
        }
        val newUrl = builder.build().toString()
        Log.d("NEW URL FOR GET ", newUrl)
        val request = Request.Builder()
            .url(newUrl)
            .get()
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

}