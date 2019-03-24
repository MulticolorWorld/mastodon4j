package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Results

class Search(private val client: MastodonClient) {

    //  GET /api/v1/search
    fun searchV1(
            q: String,
            resolve: Boolean? = null
    ): MastodonRequest<Results> {
        val parameters = Parameter().apply {
            append("q", q)
            resolve?.let {
                append("resolve", it)
            }
        }

        return MastodonRequest(
                {
                    client.get(
                            "reports",
                            parameters
                    )
                },
                {
                    client.getSerializer().fromJson(it, Report::class.java)
                }
        )
    }
}