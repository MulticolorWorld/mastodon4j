package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Account
import com.sys1yagi.mastodon4j.api.entity.Card
import com.sys1yagi.mastodon4j.api.entity.Context
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#statuses
 */
class Statuses(private val client: MastodonClient) {

    //  GET /api/v1/statuses/:id
    fun getStatus(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.get("statuses/$statusId")
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  GET /api/v1/statuses/:id/context
    fun getContext(statusId: Long): MastodonRequest<Context> {
        return MastodonRequest(
                {
                    client.get("statuses/$statusId/context")
                },
                {
                    client.getSerializer().fromJson(it, Context::class.java)
                }
        )
    }

    //  GET /api/v1/statuses/:id/card
    fun getCard(statusId: Long): MastodonRequest<Card> {
        return MastodonRequest(
                {
                    client.get("statuses/$statusId/card")
                },
                {
                    client.getSerializer().fromJson(it, Card::class.java)
                }
        )
    }

    //  GET /api/v1/reblogged_by
    fun getRebloggedBy(statusId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "statuses/$statusId/reblogged_by",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/favourited_by
    fun getFavouritedBy(statusId: Long, range: Range = Range()): MastodonRequest<Pageable<Account>> {
        return MastodonRequest<Pageable<Account>>(
                {
                    client.get(
                            "statuses/$statusId/favourited_by",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Account::class.java)
                }
        ).toPageable()
    }

    //  POST /api/v1/statuses
    fun postStatus(
            status: String,
            inReplyToId: Long? = null,
            mediaIds: List<Long>? = null,
            isSensitive: Boolean? = null,
            spoilerText: String? = null,
            visibility: Status.Visibility? = null,
            language: String? = null
    ): MastodonRequest<Status> {
        val parameters = Parameter().apply {
            append("status", status)
            inReplyToId?.let {
                append("in_reply_to_id", it)
            }
            mediaIds?.let {
                append("media_ids", it)
            }
            isSensitive?.let {
                append("sensitive", it)
            }
            spoilerText?.let {
                append("spoiler_text", it)
            }
            visibility?.let {
                append("visibility", it.value)
            }
            language?.let {
                append("language", it)
            }
        }

        return MastodonRequest(
                {
                    client.post("statuses",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  DELETE /api/v1/statuses/:id
    fun deleteStatus(statusId: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.delete("statuses/$statusId", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/reblog
    fun postReblog(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/reblog", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/unreblog
    fun postUnreblog(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/unreblog", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/favourite
    fun postFavourite(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/favourite", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/unfavourite
    fun postUnfavourite(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/unfavourite", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/pin
    fun postPin(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/pin", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/unpin
    fun postUnpin(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/unpin", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/mute
    fun postMute(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/mute", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }

    //  POST /api/v1/statuses/:id/unmute
    fun postUnmute(statusId: Long): MastodonRequest<Status> {
        return MastodonRequest(
                {
                    client.post("statuses/$statusId/unmute", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        )
    }
}
