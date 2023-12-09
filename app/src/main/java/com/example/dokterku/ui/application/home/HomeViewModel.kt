package com.example.dokterku.ui.application.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dokterku.data.DokterRepository
import com.example.dokterku.model.Dokter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: DokterRepository) : ViewModel() {

    private val _userData = MutableStateFlow(
        repository.getDokters().sortedBy { it.name }
    )
    val dokterData: StateFlow<List<Dokter>> get() = _userData

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String){
        _query.value = newQuery
        _userData.value = repository.searchDokter(_query.value)
            .sortedBy { it.name }
    }
}