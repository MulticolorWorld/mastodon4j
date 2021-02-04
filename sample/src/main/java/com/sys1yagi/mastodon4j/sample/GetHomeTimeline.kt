package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.method.Timelines
import okhttp3.OkHttpClient

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
fun main() {

    val instanceName = "instanceName"
    val accessToken = "accessToken"

    val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson())
        .accessToken(accessToken)
        .build()
    val timelines = Timelines(client)

    // page 1
    val firstPage = timelines.getHomeTimeline().execute()
    for (status in firstPage.part) {
        println(status.content)
    }

    // page 2
    val secondPage = firstPage.nextRange()
    val statuses = timelines.getHomeTimeline().execute()
    for (status in statuses.part) {
        println(status.content)
    }
}