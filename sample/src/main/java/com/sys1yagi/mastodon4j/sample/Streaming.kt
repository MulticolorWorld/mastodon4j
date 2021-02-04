package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.method.Streaming
import okhttp3.OkHttpClient

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/Streaming-API.md
 */
fun main() {

    val instanceName = "instanceName"
    val accessToken = "accessToken"

    val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson())
        .accessToken(accessToken)
        .useStreamingApi()
        .build()
    val streaming = Streaming(client)
    val handler = object : Handler {
        override fun onStatus(status: Status) {
            println(status.content)
        }
    }
    val shutdownable = streaming.user(handler)
    Thread.sleep(30 * 1000L)
    shutdownable.shutdown()
}