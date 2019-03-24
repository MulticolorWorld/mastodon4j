package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

class ScheduledStatus(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("scheduled_at") val scheduledAt: String = "",
        @SerializedName("params") val params: List<StatusParams> = emptyList(),
        @SerializedName("media_attachments") val mediaAttachments: List<Attachment> = emptyList()
) {
    class StatusParams(
            @SerializedName("text") val text: String = "",
            @SerializedName("in_reply_to_id") val inReplyToId: Long = 0L,
            @SerializedName("media_ids") val mediaIds: List<Long> = emptyList(),
            @SerializedName("sensitive") val isSensitive: Boolean = false,
            @SerializedName("spoiler_text") val spoilerText: String = "",
            @SerializedName("visibility") val visibility: Status.Visibility = Status.Visibility.Public,
            @SerializedName("scheduled_at") val scheduledAt: String = "",
            @SerializedName("application_id") val applicationId: String = ""
    )
}