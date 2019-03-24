package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#card
 */
class Card(
        @SerializedName("url") val url: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("image") val image: String = "",
        @SerializedName("type") val type: Type = Type.Link,
        @SerializedName("author_name") val authorName: String = "",
        @SerializedName("author_url") val authorUrl: String = "",
        @SerializedName("provider_name") val providerName: String = "",
        @SerializedName("provider_url") val providerUrl: String = "",
        @SerializedName("html") val html: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
) {
    enum class Type(val value: String) {
        @SerializedName("link")
        Link("link"),
        @SerializedName("photo")
        Photo("photo"),
        @SerializedName("video")
        Video("video"),
        @SerializedName("rich")
        Rich("rich")
    }
}
