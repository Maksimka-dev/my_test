package com.example.thecatapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecatapi.data.Cat
import kotlinx.coroutines.launch

class CatViewModel: ViewModel() {
    private val _items = MutableLiveData<List<Cat>>()
    val items: LiveData<List<Cat>> get() = _items
    private val limit: Int = 10
    private val page: Int = 0
    private val order: String = "DESC"

    init {
        viewModelScope.launch {
            _items.value = CatApiImpl.getListOfCats(limit, page, order)
        }
    }
    fun addNextCats(page: Int){
        viewModelScope.launch {
            _items.value = CatApiImpl.getListOfCats(limit, page, order)
        }
    }
}