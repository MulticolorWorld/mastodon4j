package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#allpication
 */
class Application(
        @SerializedName("name") val name: String = "",
        @SerializedName("website") val website: String = ""
)
