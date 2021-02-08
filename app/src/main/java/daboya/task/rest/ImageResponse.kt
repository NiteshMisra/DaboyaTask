package daboya.task.rest

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("id") var id : String,
    @SerializedName("author") var author : String,
    @SerializedName("width") var width : Long,
    @SerializedName("height") var height : Long,
    @SerializedName("url") var url : String,
    @SerializedName("download_url") var download_url : String
) {
    constructor() : this("","",0,0,"","")
}