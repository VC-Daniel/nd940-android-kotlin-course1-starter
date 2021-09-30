package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddShoeViewModel : ViewModel() {

    var shoeName: MutableLiveData<String> = MutableLiveData<String>("")
    var company: MutableLiveData<String> = MutableLiveData<String>("")
    var size: MutableLiveData<String> = MutableLiveData<String>("")
    var description: MutableLiveData<String> = MutableLiveData<String>("")

    // Event which signifies a new shoe is to be created
    private val _eventSaveShoe = MutableLiveData<Boolean>()
    val eventSaveShoe: LiveData<Boolean>
        get() = _eventSaveShoe

    // Event which signifies the user no longer wants to create a new shoe
    private val _eventCancelCreate = MutableLiveData<Boolean>()
    val eventCancelCreate: LiveData<Boolean>
        get() = _eventCancelCreate

    fun onSaveShoe() {
        _eventSaveShoe.value = true
    }

    fun onSaveShoeComplete() {
        _eventSaveShoe.value = false
    }

    fun onCancelCreate() {
        _eventCancelCreate.value = true
    }

    fun onCancelCreateComplete() {
        _eventCancelCreate.value = false
    }

    fun createShoe(): Shoe {
        val shoeSize: Double = try {
            size.value!!.toDouble()
        } catch (ERROR: NumberFormatException) {
            0.0
        }

        return Shoe(shoeName.value!!, shoeSize, company.value!!, description.value!!)

    }

}