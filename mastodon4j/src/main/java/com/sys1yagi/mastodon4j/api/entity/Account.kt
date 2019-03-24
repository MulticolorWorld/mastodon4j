package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#account
 */
class Account(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("username") val userName: String = "",
        @SerializedName("acct") val acct: String = "",
        @SerializedName("display_name") val displayName: String = "",
        @SerializedName("locked") val isLocked: Boolean = false,
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("followers_count") val followersCount: Int = 0,
        @SerializedName("following_count") val followingCount: Int = 0,
        @SerializedName("statuses_count") val statusesCount: Int = 0,
        @SerializedName("note") val note: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("avatar") val avatar: String = "",
        @SerializedName("avatar_static") val avatarStatic: String = "",
        @SerializedName("header") val header: String = "",
        @SerializedName("header_static") val headerStatic: String = "",
        @SerializedName("emojis") val emojis: List<Emoji> = emptyList(),
        @SerializedName("moved") val moved: Account? = null,
        @SerializedName("fields") val fields: List<Field> = emptyList(),
        @SerializedName("bot") val isBot: Boolean = false
) {
    class Field(
            @SerializedName("name") val name: String = "",
            @SerializedName("value") val value: String = "",
            @SerializedName("verified_at") val verifiedAt: String? = null
    )

    class Source(
            @SerializedName("privacy") val privacy: String = "",
            @SerializedName("sensitive") val sensitive: Boolean = false,
            @SerializedName("language") val language: String = "",
            @SerializedName("note") val note: String = "",
            @SerializedName("fields") val fields: List<Field> = emptyList()
    )

    class Token(
            @SerializedName("access_token") val accessToken: String = "",
            @SerializedName("token_type") val tokenType: String = "",
            @SerializedName("scope") val scope: String = "",
            @SerializedName("created_at") val createdAt: String = ""

    )
}