package com.example.favouritegitrepo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.favouritegitrepo.repository.Repository
import com.example.favouritegitrepo.ui.favouriterepo.MainViewModel

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}