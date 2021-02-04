package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#domain-blocks
 */
class DomainBlocks(private val client: MastodonClient) {

    //  GET /api/v1/domain_blocks
    fun getDomainBlocks(range: Range = Range()): MastodonRequest<Pageable<String>> {
        return MastodonRequest<Pageable<String>>(
                {
                    client.get("domain_blocks", range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, String::class.java)
                }
        ).toPageable()
    }

    //  POST /api/v1/domain_blocks
    fun postDomainBlock(domain: String): MastodonRequest<Nothing> {
        val parameters = Parameter().apply {
            append("domain", domain)
        }
        return MastodonRequest(
                {
                    client.post("domain_blocks",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }

    //  DELETE /api/v1/domain_blocks
    fun deleteDomainBlock(domain: String): MastodonRequest<Nothing> {
        val parameters = Parameter().apply {
            append("domain", domain)
        }
        return MastodonRequest(
                {
                    client.delete(
                        "domain_blocks",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }
}
