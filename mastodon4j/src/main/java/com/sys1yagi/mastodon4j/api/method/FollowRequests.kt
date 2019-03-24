package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.extension.emptyRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follow-requests
 */
class FollowRequests(private val client: MastodonClient) {

    //  GET /api/v1/follow_requests
    fun getFollowRequests(range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get("follow_requests", range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  POST /api/v1/follow_requests/:id/authorize
    fun postAuthorize(accountId: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.post("follow_requests/$accountId/authorize", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }

    fun postReject(accountId: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.post("follow_requests/$accountId/reject", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }
}
