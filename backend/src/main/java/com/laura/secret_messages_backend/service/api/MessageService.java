package com.laura.secret_messages_backend.service.api;

import com.laura.secret_messages_backend.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    UUID sendMessage(String sender, String recipient, String content);
    List<Message> getDialogue(String sender, String recipient);
}
