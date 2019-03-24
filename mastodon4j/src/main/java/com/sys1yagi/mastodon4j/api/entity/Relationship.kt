package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.annotations.SerializedName

/**
 * see more https://docs.joinmastodon.org/api/entities/#relationship
 */
class Relationship(
        @SerializedName("id") val id: Long = 0L,
        @SerializedName("following") val isFollowing: Boolean = false,
        @SerializedName("followed_by") val isFollowedBy: Boolean = false,
        @SerializedName("blocking") val isBlocking: Boolean = false,
        @SerializedName("muting") val isMuting: Boolean = false,
        @SerializedName("muting_notifications") val isMutingNotifications: Boolean = false,
        @SerializedName("requested") val isRequested: Boolean = false,
        @SerializedName("domain_blocking") val isDomainBlocking: Boolean = false,
        @SerializedName("showing_reblogs") val isShowingReblogs: Boolean = false,
        @SerializedName("endorsed") val isEndorsed: Boolean = false
)
