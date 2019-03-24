package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#mention
 */
class Mention(
        @SerializedName("url") val url: String = "",
        @SerializedName("username") val username: String = "",
        @SerializedName("acct") val acct: String = "",
        @SerializedName("id") val id: Long = 0
)
