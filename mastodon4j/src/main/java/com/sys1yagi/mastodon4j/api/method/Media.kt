package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Attachment
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#media
 */
class Media(private val client: MastodonClient) {

    //  POST /api/v1/media
    fun postMedia(
            file: MultipartBody.Part,
            description: String? = null,
            focus: String? = null
    ): MastodonRequest<Attachment> {
        val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(file)
                .apply {
                    description?.let {
                        addFormDataPart("description", description)
                    }
                    focus?.let {
                        addFormDataPart("focus", focus)
                    }
                }.build()

        return MastodonRequest(
                {
                    client.post("media", requestBody)
                },
                {
                    client.getSerializer().fromJson(it, Attachment::class.java)
                }
        )
    }

    //  PUT /api/v1/media/:id
    fun updateMedia(
            id: Long,
            description: String? = null,
            focus: String? = null
    ): MastodonRequest<Attachment> {
        val parameters = Parameter().apply {
            description?.let {
                append("description", description)
            }
            focus?.let {
                append("focus", focus)
            }
        }

        return MastodonRequest(
                {
                    client.patch("media/$id",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Attachment::class.java)
                }
        )
    }
}
