package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Filter
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#filters
 */
class Filters(private val client: MastodonClient) {

    //  GET /api/vi/filters
    fun getFilters(): MastodonRequest<List<Filters>> {
        return MastodonRequest(
                {
                    client.get("filters")
                },
                {
                    client.getSerializer().fromJson(it, Filter::class.java)
                }
        )
    }

    //  POST /api/vi/filters
    fun postFilter(
            phrase: String,
            context: List<String>,
            irreversible: Boolean? = null,
            wholeWord: Boolean? = null,
            expiresIn: Int? = null
    ): MastodonRequest<Filter> {
        val parameters = Parameter().apply {
            append("phrase", phrase)
            append("context", context)
            irreversible?.let {
                append("irreversible", it)
            }
            wholeWord?.let {
                append("whole_word", it)
            }
            expiresIn?.let {
                append("expires_in", it)
            }
        }
        return MastodonRequest(
                {
                    client.post("filters",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            )
                    )
                },
                {
                    client.getSerializer().fromJson(it, Filter::class.java)
                }
        )
    }

    //  GET /api/vi/filters/:id
    fun getFilter(id: Long): MastodonRequest<Filter> {
        return MastodonRequest(
                {
                    client.get("filters/$id")
                },
                {
                    client.getSerializer().fromJson(it, Filter::class.java)
                }
        )
    }

    //  PUT /api/vi/filters/:id
    fun putFilter(
            id: Long,
            phrase: String,
            context: List<String>,
            irreversible: Boolean? = null,
            wholeWord: Boolean? = null,
            expiresIn: Int? = null
    ): MastodonRequest<Filter> {
        val parameters = Parameter().apply {
            append("phrase", phrase)
            append("context", context)
            irreversible?.let {
                append("irreversible", it)
            }
            wholeWord?.let {
                append("whole_word", it)
            }
            expiresIn?.let {
                append("expires_in", it)
            }
        }
        return MastodonRequest(
                {
                    client.put("filters/$id",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            )
                    )
                },
                {
                    client.getSerializer().fromJson(it, Filter::class.java)
                }
        )
    }

    //  DELETE /api/vi/filters/:id
    fun deleteFilter(id: Long): MastodonRequest<Nothing> {
        return MastodonRequest(
                {
                    client.delete("filters/$id", emptyRequestBody())
                },
                {
                    client.getSerializer().fromJson(it, Nothing::class.java)
                }
        )
    }
}