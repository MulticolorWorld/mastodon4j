package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#emoji
 */
class Emoji(
        @SerializedName("shortcode") val shortcode: String = "",
        @SerializedName("static_url") val staticUrl: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("visible_in_picker") val isVisibleInPicker: Boolean = true
)
