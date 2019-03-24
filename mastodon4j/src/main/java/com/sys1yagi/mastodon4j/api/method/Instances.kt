package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.entity.Emoji
import com.sys1yagi.mastodon4j.api.entity.Instance

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
 */
class Instances(private val client: MastodonClient) {

    //  GET /api/v1/instance
    fun getInstance(): MastodonRequest<Instance> {
        return MastodonRequest(
                {
                    client.get("instance")
                },
                {
                    client.getSerializer().fromJson(it, Instance::class.java)
                }
        )
    }

    //  GET /api/v1/custom_emojis
    fun getCustomEmojis(): MastodonRequest<List<Emoji>> {
        return MastodonRequest(
                {
                    client.get("custom_emojis")
                },
                {
                    client.getSerializer().fromJson(it, Emoji::class.java)
                }
        )
    }
}