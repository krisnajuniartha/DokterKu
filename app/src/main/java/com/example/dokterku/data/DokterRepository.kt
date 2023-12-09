package com.example.dokterku.data

import com.example.dokterku.model.Dokter
import com.example.dokterku.model.DokterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DokterRepository {

    private val doktersData = mutableListOf<Dokter>()

    init {
        if (doktersData.isEmpty()){
            DokterData.dokters.forEach { user ->
                doktersData.add(user)
            }
        }
    }

    fun getDokters(): List<Dokter> {
        return doktersData
    }


    fun searchDokter(query: String): List<Dokter>{
        return doktersData.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getDokterDetailById(userId: String): Dokter {
        return doktersData.first{
            it.id == userId
        }
    }

}