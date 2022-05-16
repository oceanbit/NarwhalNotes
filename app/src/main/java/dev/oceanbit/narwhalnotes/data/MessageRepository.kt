package dev.oceanbit.narwhalnotes.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [MessageDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class MessageRepository @Inject constructor(private val messageDao: MessageDao) {

  fun getMessages() = messageDao.getMessages()

  suspend fun createMessage(message: Message) =
    messageDao.insertMessage(message)

  suspend fun deleteMessage(message: Message) =
    messageDao.deleteMessage(message)
}
