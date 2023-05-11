package com.purple.data.rooms.datasource

import com.purple.data.rooms.model.response.MembersResponse
import com.purple.data.rooms.model.response.RoomCodeResponse
import com.purple.data.rooms.model.response.RoomCreationResponse
import com.purple.data.rooms.model.response.RoomListResponse
import retrofit2.Response

interface RoomDataSource {

    suspend fun createRoom(
        roomName: String,
        userName: String,
        roomQuestion: String,
        roomPassword: String,
    ): Response<RoomCreationResponse>

    suspend fun getRoomList(): Response<List<RoomListResponse>>

    suspend fun getMembersInRoom(
        roomId: Long,
    ): Response<MembersResponse>

    suspend fun getRoomCode(roomId: Long): Response<RoomCodeResponse>

    suspend fun updateRoomName(
        userRoomId: Long,
        roomName: String,
    ): Response<Void>

    suspend fun updateUserName(
        userRoomId: Long,
        userName: String,
    ): Response<Void>

    suspend fun updatePassword(
        roomId: Long,
        password: String,
        question: String,
    ): Response<Void>

    suspend fun exitRoom(
        userRoomId: Long,
    ): Response<Void>

    suspend fun deleteRoom(
        roomId: Long,
    ): Response<Void>
}
