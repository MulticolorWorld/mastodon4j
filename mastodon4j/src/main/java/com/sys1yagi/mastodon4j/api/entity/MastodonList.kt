package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#list
 */
class MastodonList(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("title") val title: String = ""
)