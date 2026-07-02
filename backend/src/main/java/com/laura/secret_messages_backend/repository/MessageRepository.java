package com.laura.secret_messages_backend.repository;
import com.laura.secret_messages_backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CrudRepository<Message, UUID> {
    List<Message> findBySenderUsernameAndRecipientUsername(String senderUsername, String recipientUsername);

}
