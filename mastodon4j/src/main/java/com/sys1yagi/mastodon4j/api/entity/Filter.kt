package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#filter
 */
class Filter(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("phrase") val phrase: String = "",
        @SerializedName("context") val context: List<Context> = emptyList(),
        @SerializedName("expires_at") val expriresAt: String = "",
        @SerializedName("irreversible") val isIrreversible: Boolean = false,
        @SerializedName("whole_word") val isWholeWorld: Boolean = false
) {
    enum class Context(val value: String) {
        @SerializedName("home")
        Home("home"),
        @SerializedName("notifications")
        Notifications("notifications"),
        @SerializedName("public")
        Public("public"),
        @SerializedName("thread")
        Thread("thread")
    }
}