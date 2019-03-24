package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#tag
 */
class Tag(
        @SerializedName("name") val name: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("history") val history: List<History> = emptyList()
) {
    class History(
            @SerializedName("day") val day: String = "",
            @SerializedName("uses") val uses: Int = 0,
            @SerializedName("accounts") val accounts: Int = 0
    )

    class Conversation(
            @SerializedName("id") val id: Long = 0L,
            @SerializedName("accounts") val accounts: List<Account> = emptyList(),
            @SerializedName("last_status") val lastStatus: Status? = null,
            @SerializedName("unread") val isUnread: Boolean = true
    )
}
