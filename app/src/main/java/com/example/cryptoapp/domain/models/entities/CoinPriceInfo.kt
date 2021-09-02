package com.example.cryptoapp.domain.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.data.api.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoapp.domain.models.utils.convertTimestampToTime

import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE")
    val type: String? = null,

    @SerializedName("MARKET")
    val market: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    val tosymbol: String? = null,

    @SerializedName("FLAGS")
    val flags: String? = null,

    @SerializedName("PRICE")
    val price: Double? = null,

    @SerializedName("LASTUPDATE")
    val lastupdate: Long? = null,

    @SerializedName("MEDIAN")
    val median: Double? = null,

    @SerializedName("LASTVOLUME")
    val lastvolume: Double? = null,

    @SerializedName("LASTVOLUMETO")
    val lastvolumeto: Double? = null,

    @SerializedName("LASTTRADEID")
    val lasttradeid: String? = null,

    @SerializedName("VOLUMEDAY")
    val volumeday: Double? = null,

    @SerializedName("VOLUMEDAYTO")
    val volumedayto: Double? = null,

    @SerializedName("VOLUME24HOUR")
    val volume24hour: Double? = null,

    @SerializedName("VOLUME24HOURTO")
    val volume24hourto: Double? = null,

    @SerializedName("OPENDAY")
    val openday: Double? = null,

    @SerializedName("HIGHDAY")
    val highday: Double? = null,

    @SerializedName("LOWDAY")
    val lowday: Double? = null,

    @SerializedName("OPEN24HOUR")
    val open24hour: Double? = null,

    @SerializedName("HIGH24HOUR")
    val high24hour: Double? = null,

    @SerializedName("LOW24HOUR")
    val low24hour: Double? = null,

    @SerializedName("LASTMARKET")
    val lastmarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    val volumehour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    val volumehourto: Double? = null,

    @SerializedName("OPENHOUR")
    val openhour: Double? = null,

    @SerializedName("HIGHHOUR")
    val highhour: Double? = null,

    @SerializedName("LOWHOUR")
    val lowhour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOUR")
    val toptiervolume24hour: Double? = null,

    @SerializedName("TOPTIERVOLUME24HOURTO")
    val toptiervolume24hourto: Double? = null,

    @SerializedName("CHANGE24HOUR")
    val change24hour: Double? = null,

    @SerializedName("CHANGEPCT24HOUR")
    val changepct24hour: Double? = null,

    @SerializedName("CHANGEDAY")
    val changeday: Double? = null,

    @SerializedName("CHANGEPCTDAY")
    val changepctday: Double? = null,

    @SerializedName("CHANGEHOUR")
    val changehour: Double? = null,

    @SerializedName("CHANGEPCTHOUR")
    val changepcthour: Double? = null,

    @SerializedName("CONVERSIONTYPE")
    val conversiontype: String? = null,

    @SerializedName("CONVERSIONSYMBOL")
    val conversionsymbol: String? = null,

    @SerializedName("SUPPLY")
    val supply: Int? = null,

    @SerializedName("MKTCAP")
    val mktcap: Double? = null,

    @SerializedName("MKTCAPPENALTY")
    val mktcappenalty: Int? = null,

    @SerializedName("TOTALVOLUME24H")
    val totalvolume24h: Double? = null,

    @SerializedName("TOTALVOLUME24HTO")
    val totalvolume24hto: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24H")
    val totaltoptiervolume24h: Double? = null,

    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val totaltoptiervolume24hto: Double? = null,

    @SerializedName("IMAGEURL")
    val imageurl: String? = null
) {
    fun getFormattedTime(): String {
        return if (lastupdate == null) {
            ""
        } else {
            convertTimestampToTime(lastupdate)
        }
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageurl
    }
}