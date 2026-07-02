package com.laura.secret_messages_backend.controller;

import com.laura.secret_messages_backend.model.Message;
import com.laura.secret_messages_backend.service.api.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public class MessageController {
    private MessageService messageService;
    public void sendMessage(String recipient, String content) {
        messageService.sendMessage(getSender(), recipient, content);
    }

    public List<Message> getDialogue(String recipient) {
        return messageService.getDialogue(getSender(), recipient);
    }

    private String getSender() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(Object::toString)
                .orElseThrow();
    }
}