package com.example.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.database.AppDatabase
import com.example.cryptoapp.mappers.PriceListFromRawData
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.example.cryptoapp.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val mapper = PriceListFromRawData()
    val errors = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 10)
                .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
                .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
                .map { mapper.getPriceListFromRowData(it) }
                .doOnSubscribe { loading.postValue(true) }
                .doOnTerminate { loading.postValue(false) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("TEST_OF_LOADING_DATA", "Success: $it")
                }, {
                    errors.postValue(it.message)
                    Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}