package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.setData(it)
        })
        swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
            swipeRefresh.isRefreshing = false
        }
        viewModel.errors.observe(this) {
            showSnakbar()
        }
        viewModel.loading.observe(this) {
            pbLoading.isVisible = it
        }
    }

    private fun showSnakbar() {
        val snakbar = Snackbar
            .make(constraintLayout, "Ошибка загрузки данных", Snackbar.LENGTH_INDEFINITE)
            .setAction("Повторить") {
                viewModel.loadData()
            }
        snakbar.show()
    }
}