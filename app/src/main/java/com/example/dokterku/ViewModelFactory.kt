package com.example.dokterku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dokterku.data.DokterRepository
import com.example.dokterku.ui.application.detail.DetailDokterViewModel
import com.example.dokterku.ui.application.home.HomeViewModel

class ViewModelFactory(private val repository: DokterRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHEKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailDokterViewModel::class.java)) {
            return DetailDokterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class " + modelClass.name)
    }
}