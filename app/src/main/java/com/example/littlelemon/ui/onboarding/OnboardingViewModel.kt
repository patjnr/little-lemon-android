package com.example.littlelemon.ui.onboarding

import androidx.lifecycle.ViewModel
import com.example.littlelemon.data.repository.UserRepository
import com.example.littlelemon.domain.model.User

class OnboardingViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun saveUser(firstName: String, lastName: String, email: String): Boolean {
        val user = User(firstName, lastName, email)
        return userRepository.saveUser(user)
    }

    fun getUser(): User {
        return userRepository.getUser()
    }
}
