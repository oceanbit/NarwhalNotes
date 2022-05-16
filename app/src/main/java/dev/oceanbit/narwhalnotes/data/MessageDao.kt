package dev.oceanbit.narwhalnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
  @Query("SELECT * FROM message")
  fun getAll(): List<Message>

  @Query("SELECT * FROM message WHERE uid IN (:messageIds)")
  fun loadAllByIds(messageIds: IntArray): List<Message>

  @Insert
  fun insertMessage(message: Message)

  @Delete
  fun deleteMessage(user: Message)
}
