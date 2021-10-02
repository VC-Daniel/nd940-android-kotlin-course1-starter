package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** Allows the user to view all the available shoes including shoes they added. */
class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    private val _eventCreateShoe = MutableLiveData<Boolean>()

    /** Event which signifies the user wants to create a new shoe. */
    val eventCreateShoe: LiveData<Boolean>
        get() = _eventCreateShoe

    /** All shoes in the shore store including any the user has added */
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _eventCreateShoe.value = false

        // Get the pre-existing shoes in the shoe store
        initializeShoeList()
    }

    /** Populate the shoe list with the default shoes */
    private fun initializeShoeList() {

        val brand1 = "Nikae"
        val brand2 = "Basics"
        val brand3 = "Skecherz"

        _shoeList.value = mutableListOf<Shoe>(
            Shoe("Trail Blazers", 11.0, brand1, "A great pair of shoes for going off trail"),
            Shoe("Jumper", 9.5, brand2, "For all your jumping needs"),
            Shoe("Runners", 10.0, brand3, "A great pair of shoes for any runner"),
            Shoe("Speedy", 11.0, brand1, "Gives you a speed boost"),
            Shoe("Above Board", 11.0, brand1, "Only the best parts used here"),
            Shoe("High Rollers", 8.0, brand2, "Only for the classiest events"),
            Shoe("A Classic", 14.25, brand3, "A classic, enough said."),
            Shoe("Air One", 111.0, brand3, "Inspired by a legend. Become a legend."),
            Shoe("The Best", 0.5, brand2, "Simply the best around.")
        )
    }

    /** Add a user created shoe to the shore store */
    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
    }

    /** Triggered when the user wants to create a new shoe. */
    fun onCreateShoe() {
        _eventCreateShoe.value = true
    }

    /** Indicate the user has been presented with the option to create a new shoe */
    fun onCreateShoeComplete() {
        _eventCreateShoe.value = false
    }

}