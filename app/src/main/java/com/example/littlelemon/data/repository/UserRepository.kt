package com.example.littlelemon.data.repository

import android.util.Log
import com.example.littlelemon.data.local.LocalDataSource
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.domain.model.User

class UserRepository(
    private val localDataSource: LocalDataSource,
    private val sharedPreferencesManager: SharedPreferencesManager
) {

    fun saveUser(user: User): Boolean {
        return try {
            localDataSource.saveUser(user)
            sharedPreferencesManager.setLoggedIn(true)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUser(): User {
        return localDataSource.getUser()
    }
}
