package com.sys1yagi.mastodon4j.api

/**
 * see more https://docs.joinmastodon.org/api/permissions/
 */
class Scope
constructor(private vararg val scopes: Name = arrayOf(Name.ALL)) {

    sealed class Name(val scopeName: String) {
        object READ : Name("read")
        object WRITE : Name("write")
        object FOLLOW : Name("follow")
        object PUSH : Name("push")
        object ALL : Name(Scope(READ, WRITE, FOLLOW, PUSH).toString())

        data class Custom(val customScope: String) : Name(customScope)
    }

    fun validate() {
        if (scopes.size != scopes.distinct().size) {
            throw IllegalArgumentException("There is a duplicate scope. : $this")
        }
    }

    override fun toString(): String {
        return scopes.joinToString(separator = " ", transform = { it.scopeName })
    }
}
