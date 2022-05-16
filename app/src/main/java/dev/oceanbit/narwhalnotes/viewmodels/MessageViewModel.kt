package dev.oceanbit.narwhalnotes.viewmodels

import androidx.lifecycle.*
import dev.oceanbit.narwhalnotes.data.MessageRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.oceanbit.narwhalnotes.data.Message
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageListViewModel @Inject constructor(
  messageRepository: MessageRepository,
  private val messageRepositoryPriv: MessageRepository = messageRepository
) : ViewModel() {
  fun sendMessage(message: Message) {
    viewModelScope.launch {
      messageRepositoryPriv.createMessage(message)
    }
  }

  val messages: LiveData<List<Message>> = messageRepository.getMessages().asLiveData()
}
