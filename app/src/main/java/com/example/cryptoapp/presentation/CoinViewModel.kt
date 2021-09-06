package com.example.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.domain.Interactors.CoinListInteractor
import com.example.cryptoapp.domain.models.entities.CoinPriceInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val errors = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun loadData() {
        val disposable = CoinListInteractor().load()
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