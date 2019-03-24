package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#push-subscription
 */
class PushSubscription(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("endpoint") val endpoint: String = "",
        @SerializedName("server_key") val serverKey: String = "",
        @SerializedName("alerts") val alerts: Map<String, String> = emptyMap()
)