package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#attachment
 */
class Attachment(
    @SerializedName("id") val id: Long = 0L,
    @SerializedName("type") val type: String = Type.Image.value,
    @SerializedName("url") val url: String = "",
    @SerializedName("remote_url") val remoteUrl: String = "",
    @SerializedName("preview_url") val previewUrl: String = "",
    @SerializedName("text_url") val textUrl: String = "",
    @SerializedName("description") val description: String = ""
) {
    enum class Type(val value: String) {
        Image("image"),
        Video("video"),
        Gifv("gifv"),
        Unknown("unknown")
    }
}
