package com.example.cryptoapp.domain.models

import com.google.gson.annotations.SerializedName

data class CoinInfoListOfData (
    @SerializedName("Data")
    val data: List<Datum>? = null
)