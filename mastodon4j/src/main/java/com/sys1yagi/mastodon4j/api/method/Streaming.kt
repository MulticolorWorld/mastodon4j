package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.Dispatcher
import com.sys1yagi.mastodon4j.api.Handler
import com.sys1yagi.mastodon4j.api.Shutdownable
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import java.io.BufferedReader

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/Streaming-API.md
 */
class Streaming(private val client: MastodonClient) {

    //  GET /api/v1/streaming/user
    @Throws(Mastodon4jRequestException::class)
    fun user(handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/user"
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    fun public(handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/public"
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    fun localPublic(handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/public/local"
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    fun hashtag(tag: String, handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/hashtag",
            Parameter().append("tag", tag)
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    fun list(listId: Long, handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/list",
            Parameter().append("list", listId)
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    @Throws(Mastodon4jRequestException::class)
    fun direct(handler: Handler): Shutdownable {
        val response = client.get(
            "streaming/direct"
        )
        if (response.isSuccessful) {
            val reader = response.body()!!.byteStream().bufferedReader()
            val dispatcher = Dispatcher()
            dispatcher.invokeLater(
                streamingRunnable(reader, handler)
            )
            return Shutdownable(dispatcher)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    private fun streamingRunnable(reader: BufferedReader, handler: Handler): Runnable {
        return Runnable {
            while (true) {
                try {
                    val line = reader.readLine()
                    if (line == null || line.isEmpty()) {
                        continue
                    }
                    val type = line.split(":")[0].trim()
                    if (type != "event") {
                        continue
                    }
                    val event = line.split(":")[1].trim()
                    val payload = reader.readLine()
                    val payloadType = payload.split(":")[0].trim()
                    if (payloadType != "data") {
                        continue
                    }

                    val start = payload.indexOf(":") + 1
                    val json = payload.substring(start).trim()
                    if (event == "update") {
                        val status = client.getSerializer().fromJson(
                            json,
                            Status::class.java
                        )
                        handler.onStatus(status)
                    }
                    if (event == "notification") {
                        val notification = client.getSerializer().fromJson(
                            json,
                            Notification::class.java
                        )
                        handler.onNotification(notification)
                    }
                    if (event == "delete") {
                        val id = client.getSerializer().fromJson(
                            json,
                            Long::class.java
                        )
                        handler.onDelete(id)
                    }
                    if (event == "filters_changed") {
                        handler.onFiltersChanged()
                    }
                } catch (e: java.io.InterruptedIOException) {
                    break
                }
            }
            reader.close()
        }
    }
}
