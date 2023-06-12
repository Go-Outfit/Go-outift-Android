package com.example.gooutfit_android.ui.recommendation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gooutfit_android.ui.auth.viewmodel.ApiAuthConfig
import com.example.gooutfit_android.ui.auth.viewmodel.AuthResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecViewModel(application: Application) : AndroidViewModel(application)  {

    private val _genderLiveData = MutableLiveData<String>()
    val genderLiveData: LiveData<String> = _genderLiveData

    private val _weatherLiveData = MutableLiveData<String>()
    val weatherLiveData: LiveData<String> = _weatherLiveData

    private val _situationLiveData = MutableLiveData<String>()
    val situationLiveData: LiveData<String> = _situationLiveData

    private val _fashionStyleLiveData = MutableLiveData<String>()
    val fashionStyleLiveData: LiveData<String> = _fashionStyleLiveData

    private val _footwearList: MutableLiveData<List<String?>> = MutableLiveData()
    val footwearList: LiveData<List<String?>> = _footwearList

    private val _topwearList: MutableLiveData<List<String?>> = MutableLiveData()
    val topwearList: LiveData<List<String?>> = _topwearList

    private val _bottomwearList: MutableLiveData<List<String?>> = MutableLiveData()
    val bottomwearList: LiveData<List<String?>> = _bottomwearList

    fun setGender(value: String) {
        _genderLiveData.value = value
    }

    fun setWeather(value: String) {
        _weatherLiveData.value = value
    }

    fun setSituation(value: String) {
        _situationLiveData.value = value
    }

    fun setFashionStyle(value: String) {
        _fashionStyleLiveData.value = value
    }

    // Implement a method to save the data to the repository
    fun saveDataToRepository() {
        val recRequest = RecRequest().apply {
            gender = genderLiveData.value
            weather = weatherLiveData.value
            situation = situationLiveData.value
            fashion_style = fashionStyleLiveData.value
        }

        // Call your repository's saveData() method or perform any other necessary operations
        Log.e("SendData", "$recRequest")
        sendData(recRequest)
    }

    private fun sendData(recRequest: RecRequest) {
        val client = ApiRecConfig.getApiRecService()
        client.recommend(recRequest).enqueue(object : Callback<RecResponse> {
            override fun onFailure(call: Call<RecResponse>, t: Throwable) {
                Log.e("AuthViewModel", "onFailure: ${t.message.toString()}")
            }

            override fun onResponse(call: Call<RecResponse>, response: Response<RecResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    if (data != null) {
                        // The API call was successful and the response data is available
                        Log.d("FindOutfitActivity1", "Response: $data")
                        parseData(data)
                    } else {
                        // The API response was successful, but the data is null
                        Log.e("RecViewModel", "Response data is null")
                    }
                } else {
                    Log.e("RecViewModel", "onFailure: API call failed with status code ${response.code()}")
                }
            }
        })
    }

    private fun parseData(data: Data) {
        val footwearList = mutableListOf<String>()
        for (footwearGroup in data.footwear!!) {
            if (footwearGroup != null) {
                for (footwearUrl in footwearGroup) {
                    if (footwearUrl != null) {
                        Log.d("ASD", "$footwearUrl")
                        footwearList.add(footwearUrl)
                    }
                }
            }
        }
        Log.d("ABC", "$footwearList")

        val topwearList = mutableListOf<String>()
        for (topwearGroup in data.upperwear!!) {
            if (topwearGroup != null) {
                for (topwearUrl in topwearGroup) {
                    if (topwearUrl != null) {
                        topwearList.add(topwearUrl)
                    }
                }
            }
        }

        val bottomwearList = mutableListOf<String>()
        for (bottomwearGroup in data.bottomwear!!) {
            if (bottomwearGroup != null) {
                for (bottomwearUrl in bottomwearGroup) {
                    if (bottomwearUrl != null) {
                        bottomwearList.add(bottomwearUrl)
                    }
                }
            }
        }

        _footwearList.value = footwearList
        Log.d("ADE", "Footwear List: ${_footwearList.value}")
        _topwearList.value = topwearList
        Log.d("ADE", "$_topwearList")
        _bottomwearList.value = bottomwearList
        Log.d("ADE", "$_bottomwearList")
    }
}