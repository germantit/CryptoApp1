package com.example.cryptoapp.domain.models

import com.example.cryptoapp.domain.models.entities.CoinInfo
import com.google.gson.annotations.SerializedName

data class Datum (
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo? = null
)