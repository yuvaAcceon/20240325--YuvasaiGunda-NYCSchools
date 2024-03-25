package com.yuvasai.nycschools.common

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}