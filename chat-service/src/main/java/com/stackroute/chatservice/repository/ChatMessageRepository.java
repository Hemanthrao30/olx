package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

}