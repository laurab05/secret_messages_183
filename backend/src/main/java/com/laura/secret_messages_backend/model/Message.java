package com.laura.secret_messages_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="CONTENT")
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name="SENDER")
    private User sender;

    @ManyToOne
    @JoinColumn(name="RECIPIENT")
    private User recipient;

    @Column(name="SEND_TIME")
    private LocalDateTime sendTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(content, message.content) && Objects.equals(sender, message.sender) && Objects.equals(recipient, message.recipient) && Objects.equals(sendTime, message.sendTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, sender, recipient, sendTime);
    }
}
