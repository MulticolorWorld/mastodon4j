package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.PushSubscription
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 */
class Notifications(private val client: MastodonClient) {

    // GET /api/v1/notifications
    fun getNotifications(range: Range = Range()): MastodonRequest<Pageable<Notification>> {
        return MastodonRequest<Pageable<Notification>>(
                {
                    client.get(
                            "notifications",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        ).toPageable()
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: Long): MastodonRequest<Notification> {
        return MastodonRequest(
                {
                    client.get("notifications/$id")
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        )
    }

    //  POST /api/v1/notifications/clear
    fun clearNotifications(): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.post("notifications/clear", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        )
    }

    //  POST /api/v1/notifications/dismiss
    fun dismissNotification(id: Long): MastodonRequest<Nothing> {
        val parameters = Parameter().apply {
            append("id", id)
        }
        return MastodonRequest(
                {
                    client.post("notifications/dismiss",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        )
    }

    //  POST /api/v1/push/subscription
    fun postPushSubscription(
            endpoint: String,
            publicKey: String,
            authKey: String,
            isReceiveFollow: Boolean? = null,
            isReceiveFavourite: Boolean? = null,
            isReceiveReblog: Boolean? = null,
            isReceiveMention: Boolean? = null
    ): MastodonRequest<PushSubscription> {
        val parameters = Parameter().apply {
            append("subscription[endpoint]", endpoint)
            append("subscription[keys][p256dh]", publicKey)
            append("subscription[keys][auth]", authKey)
            isReceiveFollow?.let {
                append("data[alerts][follow]", it)
            }
            isReceiveFavourite?.let {
                append("data[alerts][favourite]", it)
            }
            isReceiveReblog?.let {
                append("data[alerts][reblog]", it)
            }
            isReceiveMention?.let {
                append("data[alerts][mention]", it)
            }
        }

        return MastodonRequest(
                {
                    client.post(
                        "push/subscription",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, PushSubscription::class.java)
                }
        )
    }

    //  GET /api/v1/push/subscription
    fun getSubscriotion(): MastodonRequest<PushSubscription> {
        return MastodonRequest(
                {
                    client.get("push/subscription")
                },
                {
                    client.getSerializer().fromJson(it, PushSubscription::class.java)
                }
        )
    }

    //  PUT /api/v1/push/subscription
    fun updatePushSubscripton(
            isReceiveFollow: Boolean? = null,
            isReceiveFavourite: Boolean? = null,
            isReceiveReblog: Boolean? = null,
            isReceiveMention: Boolean? = null
    ): MastodonRequest<PushSubscription> {
        val parameters = Parameter().apply {
            isReceiveFollow?.let {
                append("data[alerts][follow]", it)
            }
            isReceiveFavourite?.let {
                append("data[alerts][favourite]", it)
            }
            isReceiveReblog?.let {
                append("data[alerts][reblog]", it)
            }
            isReceiveMention?.let {
                append("data[alerts][mention]", it)
            }
        }

        return MastodonRequest(
                {
                    client.put(
                        "push/subscription",
                        parameters.build()
                            .toRequestBody("application/x-www-form-urlencoded; charset=utf-8".toMediaTypeOrNull())
                    )
                },
                {
                    client.getSerializer().fromJson(it, PushSubscription::class.java)
                }
        )
    }

    //  DELETE /api/v1/push/subscription
    fun deleteSubscription(): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.delete("push/subscription", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }
}
