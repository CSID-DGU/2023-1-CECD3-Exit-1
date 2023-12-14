package com.cecd.exitmed.domain.repository

import com.cecd.exitmed.domain.type.Pill

interface MyRepository {
    suspend fun fetchMyBookmarkedList(): Result<List<Pill>>
}
