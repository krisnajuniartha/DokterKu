package com.example.dokterku.model

data class Dokter(
    val id: String,
    val name: String,
    val email: String,
    val headline: String,
    val rating: String,
    val follower: Int,
    val photoUrl: String,
    val bannerUrl: String,
    val about: String,
)