package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#attachment
 */
class Attachment(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("type") val type: Type = Type.Image,
        @SerializedName("url") val url: String = "",
        @SerializedName("remote_url") val remoteUrl: String = "",
        @SerializedName("preview_url") val previewUrl: String = "",
        @SerializedName("text_url") val textUrl: String = "",
        @SerializedName("meta") val meta: Metadata? = null,
        @SerializedName("description") val description: String = ""
) {
    enum class Type(val value: String) {
        @SerializedName("image")
        Image("image"),
        @SerializedName("video")
        Video("video"),
        @SerializedName("gifv")
        Gifv("gifv"),
        @SerializedName("unknown")
        Unknown("unknown")
    }

    class Metadata(
            @SerializedName("small") val small: ImageDetail = ImageDetail(),
            @SerializedName("original") val original: ImageDetail = ImageDetail(),
            @SerializedName("focus") val focus: Focus = Focus()
    )

    class ImageDetail(
            @SerializedName("width") val width: Int = 0,
            @SerializedName("height") val height: Int = 0,
            @SerializedName("size") val size: String = "",
            @SerializedName("aspect") val aspect: Double = 0.0,
            @SerializedName("frame_rate") val frameRate: String = "",
            @SerializedName("duration") val duration: Double = 0.0,
            @SerializedName("bitrate") val bitrate: Int = 0
    )

    class Focus(
            @SerializedName("x") val x: Double = 0.0,
            @SerializedName("y") val y: Double = 0.0
    )

}
