package com.example.cryptoapp.mappers

import com.example.cryptoapp.domain.models.entities.CoinPriceInfo
import com.example.cryptoapp.domain.models.CoinPriceInfoRawData
import com.google.gson.Gson

class PriceListFromRawDataMapper {

    fun map(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObjects = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObjects.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObjects.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
}