package com.example.littlelemon.data.local

import com.example.littlelemon.domain.model.User

class LocalDataSource(private val sharedPreferencesManager: SharedPreferencesManager) {
    fun saveUser(user: User) {
        sharedPreferencesManager.saveUser(user)
    }

    fun getUser(): User {
        return sharedPreferencesManager.getUser()
    }

    fun clearUserData() {
        sharedPreferencesManager.clearUserData()
    }
}
