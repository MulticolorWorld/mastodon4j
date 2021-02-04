package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.MastodonList
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#lists
 */
class Lists(private val client: MastodonClient) {

    //  GET /api/v1/lists
    fun getLists(): MastodonRequest<List<MastodonList>> {
        return MastodonRequest(
                {
                    client.get("lists")
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  GET /api/v1/accounts/:id/lists
    fun getListsByMembership(accountId: Long): MastodonRequest<List<MastodonList>> {
        return MastodonRequest(
                {
                    client.get("accounts/$accountId/lists")
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  GET /api/v1/lists/:id/accounts
    fun getAccountsInList(id: Long): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get("lists/$id/accounts")
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/lists/:id
    fun getList(id: Long): MastodonRequest<MastodonList> {
        return MastodonRequest(
                {
                    client.get("lists/$id")
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  POST /api/v1/lists
    fun createList(): MastodonRequest<MastodonList> {
        return MastodonRequest(
                {
                    client.post("lists", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  PUT /api/v1/lists/:id
    fun updateList(id: Long, title: String): MastodonRequest<MastodonList> {
        val parameters = Parameter().apply {
            append("title", title)
        }
        return MastodonRequest(
                {
                    client.put("lists/$id",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  DELETE /api/v1/lists/:id
    fun deleteList(id: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.delete("lists/$id", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, MastodonList::class.java)
                }
        )
    }

    //  POST /api/v1/lists/:id/accounts
    fun addAccountsToList(id: Long, accountIds: List<Long>): MastodonRequest<Nothing> {
        val parameters = Parameter().apply {
            append("account_ids", accountIds)
        }
        return MastodonRequest(
                {
                    client.post(
                        "lists/$id/accounts",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }

    //  DELETE /api/v1/lists/:id/accounts
    fun removeAccountsFromList(id: Long, accountIds: List<Long>): MastodonRequest<Nothing> {
        val parameters = Parameter().apply {
            append("account_ids", accountIds)
        }
        return MastodonRequest(
                {
                    client.delete(
                        "lists/$id/accounts",
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