package com.sys1yagi.mastodon4j.extension

import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


// gson
inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

// okhttp
fun emptyRequestBody() = byteArrayOf().toRequestBody(null, 0, 0)