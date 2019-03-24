package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#poll
 */
class Poll(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("expires_at") val expiresAt: String = "",
        @SerializedName("expires") val isExpires: Boolean = false,
        @SerializedName("multiple") val isMultiple: Boolean = false,
        @SerializedName("votes_count") val votesCount: Int = 0,
        @SerializedName("options") val options: List<PollOption> = emptyList(),
        @SerializedName("voted") val isVoted: Boolean = false
) {
    class PollOption(
            @SerializedName("title") val title: String = "",
            @SerializedName("votes_count") val votesCount: Int = 0
    )
}