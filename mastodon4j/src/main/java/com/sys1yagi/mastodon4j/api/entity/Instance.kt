package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#instance
 */
class Instance(
        @SerializedName("uri") val uri: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("email") val email: String = "",
        @SerializedName("version") val version: String = "",
        @SerializedName("thumbnail") val thumbnail: String = "",
        @SerializedName("urls") val urls: Urls = Urls(),
        @SerializedName("stats") val stats: Stats = Stats(),
        @SerializedName("languages") val languages: List<String> = emptyList(),
        @SerializedName("contact_account") val contactAccount: Account? = null
) {
    class Urls(
            @SerializedName("streaming_api") val streamingApi: String = ""
    )

    class Stats(
            @SerializedName("user_count") val userCount: Int = 0,
            @SerializedName("status_count") val statusCount: Int = 0,
            @SerializedName("domain_count") val domainCount: Int = 0
    )
}
