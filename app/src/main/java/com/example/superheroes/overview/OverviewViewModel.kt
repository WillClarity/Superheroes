package com.example.superheroes.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroes.network.Superheroes
import com.example.superheroes.network.SuperheroesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _photoUrl = MutableLiveData<String>()
    val photoUrl: LiveData<String> = _photoUrl

    private var increment = 0

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getSuperheroes()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [Superheroes] [List] [LiveData].
     */
    private fun getSuperheroes() {
        viewModelScope.launch {
            shuffle()
        }
    }

    private suspend fun shuffle() {

        if (increment == 18)
            increment = 22
        if (increment == 23)
            increment = 0

        delay(3000)

        val listResult = SuperheroesApi.retrofitService.getSuperheroes()
        _photoUrl.value = listResult[increment].imgSrcUrl
        _name.value = listResult[increment].name

        increment++
        shuffle()

    }
}