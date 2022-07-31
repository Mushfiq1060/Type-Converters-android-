package com.example.typeconverters.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.typeconverters.db.UserDatabase
import com.example.typeconverters.model.User
import com.example.typeconverters.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(application: Application): AndroidViewModel(application) {
    private val dao = UserDatabase.getUserDatabase(application).userDao()
    private val repository = UserRepository(dao)
    suspend fun search(id: String): User? {
        var user: User? = null
        withContext(Dispatchers.IO) {
            user = repository.search(id)
        }
        return user
    }
    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }
}