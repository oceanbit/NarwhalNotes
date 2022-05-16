package dev.oceanbit.narwhalnotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Message(
  @ColumnInfo(name = "message") val message: String,
  @ColumnInfo(name = "sent") val sent: Date,
  val uidInput: Long? = null
) {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "uid")
  var uid: Long = uidInput ?: 0
}
