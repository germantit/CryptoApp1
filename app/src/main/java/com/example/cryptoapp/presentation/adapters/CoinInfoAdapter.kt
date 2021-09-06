package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.models.entities.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_info.view.*

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    private var coinInfoList: List<CoinPriceInfo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin)
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val symbolsTemplate = itemView.resources.getString(R.string.symbols_template)
        private val lastUpdateTemplate = itemView.resources.getString(R.string.last_update_template)
        fun bind(coin: CoinPriceInfo) {
            with(coin) {
                itemView.tvSymbols.text = String.format(symbolsTemplate, fromsymbol, tosymbol)
                itemView.tvPrice.text = price.toString()
                itemView.tvLastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(getFullImageUrl()).into(itemView.ivLogoCoin)
            }
        }
    }

    fun setData(info : List<CoinPriceInfo>){
        coinInfoList = info
        notifyDataSetChanged()
    }
}