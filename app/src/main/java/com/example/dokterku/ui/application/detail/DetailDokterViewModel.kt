package com.example.dokterku.ui.application.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dokterku.data.DokterRepository
import com.example.dokterku.model.Dokter
import com.example.dokterku.ui.State.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailDokterViewModel(private val repository: DokterRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Dokter>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Dokter>> get() = _uiState



    fun getDokterDetailById(userId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDokterDetailById(userId))
        }
    }

}