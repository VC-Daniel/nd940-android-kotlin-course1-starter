package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {



     val email = MutableLiveData<String>()
     val password = MutableLiveData<String>()

    // Event which signifies a new account has been created successfully
    private val _eventCreatedAccount = MutableLiveData<Boolean>()
    val eventCreatedAccount: LiveData<Boolean>
        get() = _eventCreatedAccount

    // Event which signifies the user already has an account and wants to use it
    private val _eventLoginSelected = MutableLiveData<Boolean>()
    val eventLoginSelected: LiveData<Boolean>
        get() = _eventLoginSelected

    init {
        _eventCreatedAccount.value = false
        _eventLoginSelected.value = false
    }

    fun createAccount() {
        // This is a placeholder for logic to validate with an api that the new account has been created
        _eventCreatedAccount.value = true
    }

    fun login() {
        // This is a placeholder to take the user to a login screen
        _eventLoginSelected.value = true
    }

    fun onAccountCreatedComplete() {
        clearNewAccountInput()
        _eventCreatedAccount.value = false
    }

    fun onLoginSelectedComplete() {
        clearNewAccountInput()
        _eventLoginSelected.value = false
    }

    private fun clearNewAccountInput()
    {
        email.value = ""
        password.value = ""
    }
}