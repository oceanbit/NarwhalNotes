package dev.oceanbit.narwhalnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
  @Query("SELECT * FROM message")
  fun getMessages(): Flow<List<Message>>

  @Insert
  suspend fun insertMessage(message: Message)

  @Query("DELETE FROM message WHERE uid IN (:messageIds)")
  suspend fun deleteMessages(messageIds: List<Long>): Void
}
