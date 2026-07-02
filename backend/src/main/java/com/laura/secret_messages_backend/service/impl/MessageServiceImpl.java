package com.laura.secret_messages_backend.service.impl;

import com.laura.secret_messages_backend.model.Message;
import com.laura.secret_messages_backend.repository.MessageRepository;
import com.laura.secret_messages_backend.repository.UserRepository;
import com.laura.secret_messages_backend.service.api.MessageService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository repository, UserRepository userRepository) {
        this.messageRepository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public UUID sendMessage(String sender, String recipient, String content) {
        Message message = new Message();
        message.setRecipient(userRepository.findByUsername(recipient).orElseThrow());
        message.setSender(userRepository.findByUsername(sender).orElseThrow());
        message.setContent(content);
        message.setSendTime(LocalDateTime.now());
        messageRepository.save(message);
        return message.getId();
    }

    @Override
    public List<Message> getDialogue(String sender, String recipient) {
        return messageRepository.findBySenderUsernameAndRecipientUsername(sender, recipient)
                .stream().sorted(Comparator.comparing(Message::getSendTime))
                .collect(Collectors.toList());
    }
}