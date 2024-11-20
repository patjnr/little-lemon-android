package com.example.littlelemon.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.littlelemon.domain.model.User

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "LittleLemonPreferences"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putString(KEY_FIRST_NAME, user.firstName)
            putString(KEY_LAST_NAME, user.lastName)
            putString(KEY_EMAIL, user.email)
            putBoolean(KEY_IS_LOGGED_IN, true)  // Set user as logged in
            apply()
        }
    }

    fun getUser(): User {
        return User(
            firstName = sharedPreferences.getString(KEY_FIRST_NAME, "") ?: "",
            lastName = sharedPreferences.getString(KEY_LAST_NAME, "") ?: "",
            email = sharedPreferences.getString(KEY_EMAIL, "") ?: ""
        )
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }
    fun getFirstName(): String {
        return sharedPreferences.getString(KEY_FIRST_NAME, "") ?: ""
    }

    fun getLastName(): String {
        return sharedPreferences.getString(KEY_LAST_NAME, "") ?: ""
    }

    fun getEmail(): String {
        return sharedPreferences.getString(KEY_EMAIL, "") ?: ""
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}
