package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#status
 */
class Status(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("uri") val uri: String = "",
        @SerializedName("url") val url: String = "",
        @SerializedName("account") val account: Account = Account(),
        @SerializedName("in_reply_to_id") val inReplyToId: Long = 0L,
        @SerializedName("in_reply_to_account_id") val inReplyToAccountId: Long = 0L,
        @SerializedName("reblog") val reblog: Status? = null,
        @SerializedName("content") val content: String = "",
        @SerializedName("created_at") val createdAt: String = "",
        @SerializedName("emojis") val emojis: List<Emoji> = emptyList(),
        @SerializedName("replies_count") val repliesCount: Int = 0,
        @SerializedName("reblogs_count") val reblogsCount: Int = 0,
        @SerializedName("favourites_count") val favouritesCount: Int = 0,
        @SerializedName("reblogged") val isReblogged: Boolean = false,
        @SerializedName("favourited") val isFavourited: Boolean = false,
        @SerializedName("muted") val isMuted: Boolean = false,
        @SerializedName("sensitive") val isSensitive: Boolean = false,
        @SerializedName("spoiler_text") val spoilerText: String = "",
        @SerializedName("visibility") val visibility: Visibility = Visibility.Public,
        @SerializedName("media_attachments") val mediaAttachments: List<Attachment> = emptyList(),
        @SerializedName("mentions") val mentions: List<Mention> = emptyList(),
        @SerializedName("tags") val tags: List<Tag> = emptyList(),
        @SerializedName("card") val card: Card? = null,
        @SerializedName("poll") val poll: Poll? = null,
        @SerializedName("application") val application: Application = Application(),
        @SerializedName("language") val language: String = "",
        @SerializedName("pinned") val isPinned: Boolean = false
) {
    enum class Visibility(val value: String) {
        @SerializedName("public")
        Public("public"),
        @SerializedName("unlisted")
        Unlisted("unlisted"),
        @SerializedName("private")
        Private("private"),
        @SerializedName("direct")
        Direct("direct")
    }
}
