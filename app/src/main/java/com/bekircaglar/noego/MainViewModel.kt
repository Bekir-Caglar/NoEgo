package com.bekircaglar.noego

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _isEgoOn = MutableLiveData<Boolean>(true)
    val isEgoOn: LiveData<Boolean> = _isEgoOn

    private val _isHappinessOn = MutableLiveData<Boolean>(false)
    val isHappinessOn: LiveData<Boolean> = _isHappinessOn

    private val _isOptimismOn = MutableLiveData<Boolean>(false)
    val isOptimismOn: LiveData<Boolean> = _isOptimismOn

    private val _isKindnessOn = MutableLiveData<Boolean>(false)
    val isKindnessOn: LiveData<Boolean> = _isKindnessOn

    private val _isGivingOn = MutableLiveData<Boolean>(false)
    val isGivingOn: LiveData<Boolean> = _isGivingOn

    private val _isRespectOn = MutableLiveData<Boolean>(false)
    val isRespectOn: LiveData<Boolean> = _isRespectOn

    private val _selectedItems = MutableLiveData<MutableList<String>>(mutableListOf("MainScreen"))
    val selectedItems: LiveData<MutableList<String>> = _selectedItems

    fun toggleSwitch(item: String, isChecked: Boolean) {
        if (item == "ego" && isChecked) {
            resetAll()
        } else {
            when (item) {
                "ego" -> _isEgoOn.value = isChecked
                "happiness" -> _isHappinessOn.value = isChecked
                "optimism" -> _isOptimismOn.value = isChecked
                "kindness" -> _isKindnessOn.value = isChecked
                "giving" -> _isGivingOn.value = isChecked
                "respect" -> _isRespectOn.value = isChecked
            }

            if (item != "ego") {
                if (isChecked) {
                    addItem(item)
                } else {
                    removeItem(item)
                }
            }
        }
    }

    private fun resetAll() {
        _isHappinessOn.value = false
        _isOptimismOn.value = false
        _isKindnessOn.value = false
        _isGivingOn.value = false
        _isRespectOn.value = false
        _selectedItems.value = mutableListOf("MainScreen")
        _isEgoOn.value = true
    }

    private fun addItem(item: String) {
        val items = _selectedItems.value ?: mutableListOf()
        if (!items.contains(item)) {
            items.add(item)
            if (items.size > 5) {
                items.removeAt(items.size - 1)
            }
            _selectedItems.value = items
        }
    }

    private fun removeItem(item: String) {
        val items = _selectedItems.value ?: mutableListOf()
        items.remove(item)
        _selectedItems.value = items
    }
}
