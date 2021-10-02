package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/** Facilitate logging in or creating a new account */
class LoginViewModel : ViewModel() {

    // These variables are connected with two way data binding to the UI so the user can specify
    // account information for creating a new account
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _eventCreatedAccount = MutableLiveData<Boolean>()

    /** Event which signifies a new account has been created successfully. */
    val eventCreatedAccount: LiveData<Boolean>
        get() = _eventCreatedAccount

    private val _eventLoginSelected = MutableLiveData<Boolean>()

    /** Event which signifies the user already has an account and wants to use it. */
    val eventLoginSelected: LiveData<Boolean>
        get() = _eventLoginSelected

    init {
        _eventCreatedAccount.value = false
        _eventLoginSelected.value = false
    }

    /** Signifies a new account  has been created*/
    fun createAccount() {
        // This is a placeholder for logic to validate with an api that the new account has been created
        _eventCreatedAccount.value = true
    }

    /** Signifies the user has logged in with an existing account */
    fun login() {
        // This is a placeholder to handle the user logging in with an existing account
        _eventLoginSelected.value = true
    }

    /** Indicate that a new account  has been created*/
    fun onAccountCreatedComplete() {
        clearNewAccountInput()
        _eventCreatedAccount.value = false
    }

    /** Indicate that the user has logged in with an existing account*/
    fun onLoginSelectedComplete() {
        clearNewAccountInput()
        _eventLoginSelected.value = false
    }

    /** Clear the new account information */
    private fun clearNewAccountInput() {
        email.value = ""
        password.value = ""
    }
}