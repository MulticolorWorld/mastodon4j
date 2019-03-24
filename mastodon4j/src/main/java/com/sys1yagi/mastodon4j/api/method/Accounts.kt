package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Relationship
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#accounts
 */
class Accounts(private val client: MastodonClient) {

    // GET /api/v1/accounts/:id
    fun getAccount(accountId: Long): MastodonRequest<Account> {
        return MastodonRequest(
                { client.get("accounts/$accountId") },
                { client.getSerializer().fromJson(it, Account::class.java) }
        )
    }

    // GET /api/v1/accounts/verify_credentials
    fun getVerifyCredentials(): MastodonRequest<Account> {
        return MastodonRequest(
                { client.get("accounts/verify_credentials") },
                { client.getSerializer().fromJson(it, Account::class.java) }
        )
    }

    // PATCH /api/v1/accounts/update_credentials
    fun updateCredential(displayName: String? = null,
                         note: String? = null,
                         avatar: String? = null,
                         header: String? = null,
                         locked: Boolean? = null,
                         fieldsAttributes: List<Pair<String, String>>? = null
    ): MastodonRequest<Account> {
        val parameters = Parameter().apply {
            displayName?.let {
                append("display_name", it)
            }
            note?.let {
                append("note", it)
            }
            avatar?.let {
                append("avatar", it)
            }
            header?.let {
                append("header", it)
            }
            locked?.let {
                append("locked", it)
            }
            fieldsAttributes?.let {
                for ((index, attribute) in fieldsAttributes.withIndex()) {
                    append("fields_attributes[$index][name]", attribute.first)
                    append("fields_attributes[$index][value]", attribute.second)
                }
            }
        }
        return MastodonRequest(
                {
                    client.patch("accounts/update_credentials",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }

    //  GET /api/v1/accounts/:id/followers
    fun getFollowers(accountId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "accounts/$accountId/followers",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/accounts/:id/following
    fun getFollowing(accountId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "accounts/$accountId/following",
                            range.toParameter())
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    // GET /api/v1/accounts/:id/statuses
    fun getStatuses(
            accountId: Long,
            onlyMedia: Boolean? = null,
            pinned: Boolean? = null,
            excludeReplies: Boolean? = null,
            range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        val parameters = range.toParameter().apply {
            onlyMedia?.let {
                append("only_media", it)
            }
            pinned?.let {
                append("pinned", it)
            }
            excludeReplies?.let {
                append("exclude_replies", it)
            }
        }
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "accounts/$accountId/statuses",
                            parameters
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    // POST /api/v1/accounts/:id/follow
    fun postFollow(
            accountId: Long,
            reblogs: Boolean? = null
    ): MastodonRequest<Relationship> {
        val parameters = Parameter().apply {
            reblogs?.let {
                append("reblogs", it)
            }
        }
        return MastodonRequest(
                {
                    client.post(
                            "accounts/$accountId/follow",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            )
                    )
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unfollow
    fun postUnFollow(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/unfollow", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/block
    fun postBlock(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/block", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unblock
    fun postUnblock(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/unblock", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/mute
    fun postMute(
            accountId: Long,
            notifications: Boolean? = null
    ): MastodonRequest<Relationship> {
        val parameters = Parameter().apply {
            notifications?.let {
                append("notifications", it)
            }
        }
        return MastodonRequest(
                {
                    client.post(
                            "accounts/$accountId/mute",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            )
                    )
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unmute
    fun postUnmute(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/unmute", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/pin
    fun postEndorse(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/pin", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  POST /api/v1/accounts/:id/unpin
    fun postUnendorse(accountId: Long): MastodonRequest<Relationship> {
        return MastodonRequest(
                {
                    client.post("accounts/$accountId/unpin", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    //  GET /api/v1/accounts/relationships
    fun getRelationships(accountIds: List<Long>): MastodonRequest<List<Relationship>> {
        return MastodonRequest(
                {
                    client.get(
                            "accounts/relationships",
                            Parameter().append("id", accountIds)
                    )
                },
                {
                    client.getSerializer().fromJson(it, Relationship::class.java)
                }
        )
    }

    // GET /api/v1/accounts/search
    fun getAccountSearch(
            query: String,
            limit: Int? = null,
            following: Boolean? = null
    ): MastodonRequest<List<Account>> {
        val parameter = Parameter().apply {
            append("q", query)
            limit?.let {
                append("limit", it)
            }
            following?.let {
                append("following", it)
            }
        }
        return MastodonRequest(
                {
                    client.get(
                            "accounts/search",
                            parameter
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        )
    }
}
