package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.extension.emptyRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follow-suggestions
 */
class FollowSuggestions(private val client: MastodonClient) {

    //  GET /api/v1/suggestions
    fun getFollowSuggestions(): MastodonRequest<List<Account>> {
        return MastodonRequest(
                {
                    client.get("suggestions")
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }

    //  DELETE /api/v1/suggestions/:account_id
    fun deleteSuggestions(accountId: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.delete("suggestions/$accountId", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }
}