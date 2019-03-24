package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#notification
 */
class Notification(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("type") val type: Type = Type.Mention,
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("account") val account: Account = Account(),
        @SerializedName("status") val status: Status? = null
) {
    enum class Type(val value: String) {
        @SerializedName("mention")
        Mention("mention"),
        @SerializedName("reblog")
        Reblog("reblog"),
        @SerializedName("favourites")
        Favourite("favourite"),
        @SerializedName("follow")
        Follow("follow")
    }
}
