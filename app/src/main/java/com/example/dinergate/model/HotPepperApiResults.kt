package com.example.dinergate.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotPepperApiResult(
    val results: Results
)

@Serializable
data class Results(
    @SerialName(value = "api_version")
    val apiVersion: String = "",
    @SerialName(value = "results_available")
    val resultsAvailable: Int = 0,
    @SerialName(value = "results_returned")
    val resultsReturned: Int = 10,
    @SerialName(value = "results_start")
    val resultsStart: Int = 1,
    val shop: List<Shop>
)

@Serializable
data class Shop(
    val access: String = "",
    val address: String = "",
    val band: String = "", //可・不可
    @SerialName(value = "barrier_free")
    val barrierFree: String = "", //あり・なし
    val budget: Budget,
    @SerialName(value = "budget_memo")
    val budgetMemo: String = "",
    val capacity: Int = 0,
    val card: String = "", //利用可・利用不可
    val catch: String = "",
    val charter: String = "", //貸切可・貸切不可
    val child: String = "",
    val close: String = "",
    @SerialName(value = "coupon_urls")
    val couponUrls: CouponUrls,
    val course: String = "", //あり・なし？（なし未確認）
    val english: String = "", //あり・なし
    @SerialName(value = "free_drink")
    val freeDrink: String = "", //あり・なし
    @SerialName(value = "free_food")
    val freeFood: String = "", //あり・なし
    val genre: Genre,
    val horigotatsu: String = "",
    val id: String = "",
    val karaoke: String = "", //あり・なし
    @SerialName(value = "ktai_coupon")
    val ktaiCoupon: Int = 0, //0（なし）・1（あり）
    @SerialName(value = "large_area")
    val largeArea: LargeArea,
    @SerialName(value = "large_service_area")
    val largeServiceArea: LargeServiceArea,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    @SerialName(value = "logo_image")
    val logoImage: String = "",
    val lunch: String = "", //あり・なし
    @SerialName(value = "middle_area")
    val middleArea: MiddleArea,
    val midnight: String = "", //営業している・営業していない
    @SerialName(value = "mobile_access")
    val mobileAccess: String = "",
    val name: String = "",
    @SerialName(value = "name_kana")
    val nameKana: String = "",
    @SerialName(value = "non_smoking")
    val nonSmoking: String = "",
    val open: String = "",
    @SerialName(value = "other_memo")
    val otherMemo: String = "",
    val parking: String = "", //あり・なし
    @SerialName(value = "party_capacity")
    val partyCapacity: Int = 0,
    val pet: String = "",
    val photo: Photo,
    @SerialName(value = "private_room")
    val privateRoom: String = "", //あり・なし
    @SerialName(value = "service_area")
    val serviceArea: ServiceArea,
    @SerialName(value = "shop_detail_memo")
    val shopDetailMemo: String = "",
    val show: String = "", //あり・なし？（あり未確認）
    @SerialName(value = "small_area")
    val smallArea: SmallArea,
    @SerialName(value = "station_name")
    val stationName: String,
    @SerialName(value = "sub_genre")
    val subGenre: SubGenre? = null, //subGenreフィールドは空の場合があるため、null許容とする
    val tatami: String = "", //あり・なし
    val tv: String = "", //あり・なし
    val urls: Urls,
    val wedding: String = "",
    val wifi: String = "" //あり・なし
)

@Serializable
data class Budget(
    val average: String = "",
    val code: String = "",
    val name: String = ""
)

@Serializable
data class CouponUrls(
    val pc: String = "",
    val sp: String = ""
)

@Serializable
data class Genre(
    val catch: String = "",
    val code: String = "",
    val name: String = ""
)

@Serializable
data class LargeArea(
    val code: String = "",
    val name: String = ""
)

@Serializable
data class LargeServiceArea(
    val code: String = "",
    val name: String = ""
)

@Serializable
data class MiddleArea(
    val code: String = "",
    val name: String = ""
)

@Serializable
data class Photo(
    val mobile: Mobile,
    val pc: Pc
)

@Serializable
data class ServiceArea(
    val code: String = "",
    val name: String = ""
)

@Serializable
data class SmallArea(
    val code: String = "",
    val name: String = ""
)

@Serializable
data class Urls(
    val pc: String = ""
)

@Serializable
data class Mobile(
    val l: String = "",
    val s: String = ""
)

@Serializable
data class SubGenre(
    val code: String? = null,
    val name: String? = null
)

@Serializable
data class Pc(
    val l: String = "",
    val m: String = "",
    val s: String = ""
)