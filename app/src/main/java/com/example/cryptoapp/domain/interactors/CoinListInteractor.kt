package com.example.cryptoapp.domain.interactors

import com.example.cryptoapp.data.api.ApiFactory
import com.example.cryptoapp.domain.models.entities.CoinPriceInfo
import com.example.cryptoapp.data.mappers.PriceListFromRawDataMapper
import io.reactivex.Single

class CoinListInteractor {
    fun load() : Single<List<CoinPriceInfo>> {
        return ApiFactory.apiService.getTopCoinsInfo(limit = 10)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { PriceListFromRawDataMapper().map(it) }
    }
}