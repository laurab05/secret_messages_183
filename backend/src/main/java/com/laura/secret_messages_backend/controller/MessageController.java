package com.laura.secret_messages_backend.controller;

import com.laura.secret_messages_backend.model.Message;
import com.laura.secret_messages_backend.service.api.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send/{recipient}")
    public void sendMessage(@PathVariable("recipient") String recipient, @RequestBody String content) {
        messageService.sendMessage(getSender(), recipient, content);
    }

    @GetMapping("/dialogue/{recipient}")
    public List<Message> getDialogue(@PathVariable("recipient") String recipient) {
        return messageService.getDialogue(getSender(), recipient);
    }

    @GetMapping("/sender")
    private String getSender() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (UserDetails) principal)
                .map(UserDetails::getUsername)
                .orElseThrow();
    }
}
