package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#error
 */
class Error(
        @SerializedName("error") val error: String = ""
)
