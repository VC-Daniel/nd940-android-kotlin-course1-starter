package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** Facilitates adding a new shoe. */
class AddShoeViewModel : ViewModel() {

    // These variables are connected with two way data binding to the UI so the user can specify
    // the information about a new shoe
    var shoeName: MutableLiveData<String> = MutableLiveData<String>("")
    var company: MutableLiveData<String> = MutableLiveData<String>("")
    var size: MutableLiveData<String> = MutableLiveData<String>("")
    var description: MutableLiveData<String> = MutableLiveData<String>("")

    private val _eventSaveShoe = MutableLiveData<Boolean>()

    /** Event which signifies a new shoe is to be created. */
    val eventSaveShoe: LiveData<Boolean>
        get() = _eventSaveShoe


    private val _eventCancelCreate = MutableLiveData<Boolean>()

    /** Event which signifies the user no longer wants to create a new shoe. */
    val eventCancelCreate: LiveData<Boolean>
        get() = _eventCancelCreate

    /** Triggered when a new shoe is being saved. */
    fun onSaveShoe() {
        _eventSaveShoe.value = true
    }

    /** Indicate the shoe has been saved. */
    fun onSaveShoeComplete() {
        _eventSaveShoe.value = false
    }

    /** Triggered when the user no longer wants to create a new shoe. */
    fun onCancelCreate() {
        _eventCancelCreate.value = true
    }

    /** Indicate the creation of a new shoe has been canceled. */
    fun onCancelCreateComplete() {
        _eventCancelCreate.value = false
    }

    /** Returns a new shoe with the specified information. */
    fun createShoe(): Shoe {

        // Verify the shoe size specified is valid
        val shoeSize: Double = try {
            size.value!!.toDouble()
        } catch (ERROR: NumberFormatException) {
            0.0
        }

        return Shoe(shoeName.value!!, shoeSize, company.value!!, description.value!!)

    }

}