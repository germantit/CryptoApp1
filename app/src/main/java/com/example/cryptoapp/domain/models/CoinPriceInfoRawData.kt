package com.example.cryptoapp.domain.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRawData (
    @SerializedName("RAW")
    val coinPriceInfoJsonObject: JsonObject? = null
)