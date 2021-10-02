package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
/** Holds information about a specific shoe in the shoe store such as the shoe's name, size,
 * the company that manufacturer the shoe and a description*/
data class Shoe(
    var name: String, var size: Double, var company: String, var description: String,
    val images: List<String> = mutableListOf()
) : Parcelable


