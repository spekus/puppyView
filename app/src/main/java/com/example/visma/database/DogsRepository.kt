package com.example.visma.database

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.visma.R
import com.example.visma.model.Dogs
import com.google.gson.Gson

class DogsRepository(val application: Application) {

    private val _dogs: MutableLiveData<Dogs> = MutableLiveData()

    val dogs : LiveData<Dogs>
            get() = _dogs

     fun refreshDogs() {
            val gson = Gson()
            val rawText =  application.resources.openRawResource(R.raw.dog_urls)
                .bufferedReader().use { it.readText() }

            val dogs = gson.fromJson(rawText, Dogs::class.java)
            _dogs.value = dogs
    }
}