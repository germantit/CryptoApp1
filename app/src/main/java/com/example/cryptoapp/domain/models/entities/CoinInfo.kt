package com.example.cryptoapp.domain.models.entities

import com.google.gson.annotations.SerializedName


data class CoinInfo (
    @SerializedName("Name")
    val name: String? = null
)