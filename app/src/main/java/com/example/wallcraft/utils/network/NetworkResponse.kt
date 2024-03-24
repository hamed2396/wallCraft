package com.example.wallcraft.utils.network

import com.crazylegend.kotlinextensions.string.isNotNullOrEmpty
import com.example.wallcraft.utils.Constants.ERROR_400
import com.example.wallcraft.utils.Constants.ERROR_401
import com.example.wallcraft.utils.Constants.ERROR_403
import com.example.wallcraft.utils.Constants.ERROR_404
import com.example.wallcraft.utils.Constants.ERROR_500
import com.example.wallcraft.utils.network.NetworkStatus.Error
import com.example.wallcraft.utils.network.NetworkStatus.Success
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class NetworkResponse @Inject constructor() {

    fun <T> handleResponse(response: Response<T>): NetworkStatus<T> {
        return when {
            response.isSuccessful -> Success(response.body()!!)
            response.code() == 400 -> Error(ERROR_400)
            response.code() == 401 -> Error(ERROR_401)
            response.code() == 403 -> Error(ERROR_403)
            response.code() == 404 -> Error(ERROR_404)
            response.code() in 500..503 -> Error(ERROR_500)



            else -> {
                Error(response.message())
            }
        }
    }
}