package com.example.cryptoapp.data.api.repositories

import com.example.cryptoapp.data.api.ApiFactory
import com.example.cryptoapp.domain.models.entities.CoinPriceInfo
import com.example.cryptoapp.mappers.PriceListFromRawDataMapper
import io.reactivex.Single

class LoadDataFromApi {
    fun load() : Single<List<CoinPriceInfo>> {
        return ApiFactory.apiService.getTopCoinsInfo(limit = 10)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { PriceListFromRawDataMapper().map(it) }
    }
}