package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#context
 */
class Context(
        @SerializedName("ancestors") val ancestors: List<Status> = emptyList(),
        @SerializedName("descendants") val descendants: List<Status> = emptyList()
)
