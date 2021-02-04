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
    val clientId = "clientId"
    val clientSecret = "clientSecret"
    val redirectUrl = "redirectUrl"

    val client = MastodonClient.Builder(instanceName, OkHttpClient.Builder(), Gson()).build()
    val apps = Apps(client)
    val oauthUrl = apps.getOAuthUrl(clientId, Scope(Scope.Name.ALL), redirectUrl)

    // access oauthUrl and get code
    val code = "code"
    val accessToken = apps.getAccessToken(clientId, clientSecret, redirectUrl, code).execute()

    println("accessToken=" + accessToken.accessToken)
}