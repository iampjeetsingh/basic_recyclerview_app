package io.github.iampjeetsingh.assignment.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.iampjeetsingh.assignment.repository.UserRepository

class MainViewModelFactory(private var userRepository: UserRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository, context) as T
    }
}