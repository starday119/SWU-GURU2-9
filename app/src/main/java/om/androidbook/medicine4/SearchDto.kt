package om.androidbook.medicine4

import com.google.gson.annotations.SerializedName

data class SearchDto(

    @SerializedName("lastBuildDate")
    val lastBuildDate: String,

    @SerializedName ("total")
    var total :Int,

    @SerializedName ("items")
    var items :List<Item>
)

data class Item(
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("roadAddress") val roadAddress: String?,
)