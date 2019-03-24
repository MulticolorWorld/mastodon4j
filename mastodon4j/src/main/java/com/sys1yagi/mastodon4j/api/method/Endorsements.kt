package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#endorsements
 */
class Endorsements(private val client: MastodonClient) {

    //  GET /api/v1/endorsements
    fun getEndorsements(
            range: Range = Range()
    ): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "endorsements",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }
}