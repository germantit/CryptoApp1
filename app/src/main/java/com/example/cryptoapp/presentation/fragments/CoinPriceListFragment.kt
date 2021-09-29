package com.example.cryptoapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.CoinViewModel
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_coin_price_list.*

class CoinPriceListFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coin_price_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CoinInfoAdapter()
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
            swipeRefresh.isRefreshing = false
        }
        viewModel.errors.observe(viewLifecycleOwner) {
            showSnakbar()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            pbLoading.isVisible = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun showSnakbar() {
        val snakbar = Snackbar
            .make(constraintLayout, getString(R.string.error_load_data), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.repeat)) {
                viewModel.loadData()
            }
        snakbar.show()
    }

}