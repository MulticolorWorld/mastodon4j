package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Account
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#follows
 */
class Follows(private val client: MastodonClient) {

    //  POST /api/v1/follows
    fun postFollow(uri: String): MastodonRequest<Account> {
        val parameters = Parameter().apply {
            append("uri", uri)
        }
        return MastodonRequest(
                {
                    client.post("follows",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }
}
