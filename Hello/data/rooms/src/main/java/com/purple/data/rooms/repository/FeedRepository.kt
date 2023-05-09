package com.purple.data.rooms.repository

import kotlinx.coroutines.flow.Flow
import java.util.*

interface FeedRepository {

    fun getQuestion(roomId: Long, date: Date): Flow<String>

    suspend fun updateQuestion(roomId: Long, date: Date): String
}
