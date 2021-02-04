package com.sys1yagi.mastodon4j.sample

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Scope
import com.sys1yagi.mastodon4j.api.method.Apps
import okhttp3.OkHttpClient

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/OAuth-details.md
 */
fun main() {

    val instanceName = "instanceName"

    val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson()).build()
    val apps = Apps(client)
    val registration = apps.createApp(
        "mastodon4j-sample-app",
        "urn:ietf:wg:oauth:2.0:oob",
        Scope(Scope.Name.ALL)
    ).execute()

    println("instance=" + client.getInstanceName())
    println("client_id=" + registration.clientId)
    println("client_secret=" + registration.clientSecret)
}