package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#results
 */
class Results(
        @SerializedName("accounts") val accounts: List<Account> = emptyList(),
        @SerializedName("statuses") val statuses: List<Status> = emptyList(),
        @SerializedName("hashtags") val hashtags: List<Tag> = emptyList()
)
