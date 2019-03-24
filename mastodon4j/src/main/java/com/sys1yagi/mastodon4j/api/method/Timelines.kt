package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(private val client: MastodonClient) {

    //  GET /api/v1/timelines/home
    fun getHomeTimeline(range: Range = Range()): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "timelines/home",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/timelines/public
    fun getPublicTimeLine(
            local: Boolean? = null,
            onlyMedia: Boolean? = null,
            range: Range
    ): MastodonRequest<Pageable<Status>> {
        val parameter = range.toParameter().apply {
            local?.let {
                append("local", local)
            }
            onlyMedia?.let {
                append("only_media", onlyMedia)
            }
        }

        return MastodonRequest<Pageable<Status>>(
                {
                    client.get("timelines/public", parameter)
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/timelines/tag/:hashtag
    fun getHashtagTimeline(
            hashtag: String,
            local: Boolean? = null,
            onlyMedia: Boolean? = null,
            range: Range
    ): MastodonRequest<Pageable<Status>> {
        val parameter = range.toParameter().apply {
            local?.let {
                append("local", local)
            }
            onlyMedia?.let {
                append("only_media", onlyMedia)
            }
        }

        return MastodonRequest<Pageable<Status>>(
                {
                    client.get("timelines/tag/$hashtag", parameter)
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/timelines/list/:list_id
    fun getListTimeline(
            listId: Long,
            range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "timelines/list/$listId",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }

    //  GET /api/v1/timelines/direct
    fun getDirectMessageTimeline(
            range: Range = Range()
    ): MastodonRequest<Pageable<Status>> {
        return MastodonRequest<Pageable<Status>>(
                {
                    client.get(
                            "timelines/direct",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Status::class.java)
                }
        ).toPageable()
    }
}
