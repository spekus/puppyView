package com.example.visma.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.visma.R
import com.example.visma.model.Dogs
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogsRepository(val application: Application) {
    private val _dogs: MutableLiveData<Dogs> = MutableLiveData()

    val dogs: LiveData<Dogs>
        get() = _dogs

    suspend fun refreshDogs() {
        val dogs = withContext(Dispatchers.IO) {
            val gson = Gson()
            val rawText = application.resources.openRawResource(R.raw.dog_urls)
                .bufferedReader().use { it.readText() }

            gson.fromJson(rawText, Dogs::class.java)
        }
        _dogs.value = dogs
    }
}