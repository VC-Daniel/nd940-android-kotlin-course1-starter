package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()

    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        initializeShoeList()
    }

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

    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)

    }

}