package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import okhttp3.MediaType
import okhttp3.RequestBody

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#reports
 */
class Reports(private val client: MastodonClient) {

    // GET /api/v1/reports
    fun getReports(range: Range = Range()): MastodonRequest<Pageable<Report>> {
        return MastodonRequest<Pageable<Report>>(
                {
                    client.get(
                            "reports",
                            range.toParameter()
                    )
                },
                {
                    client.getSerializer().fromJson(it, Report::class.java)
                }
        ).toPageable()
    }

    // POST /api/v1/reports
    fun postReport(
            accountId: Long,
            statusId: Long,
            comment: String
    ): MastodonRequest<Report> {
        val parameters = Parameter().apply {
            append("account_id", accountId)
            append("status_ids", statusId)
            append("comment", comment)
        }
        return MastodonRequest(
                {
                    client.post("reports",
                            RequestBody.create(
                                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                                    parameters.build()
                            ))
                },
                {
                    client.getSerializer().fromJson(it, Report::class.java)
                }
        )
    }
}
