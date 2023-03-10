package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.BuyerChatWithProductId;
import com.stackroute.chatservice.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    List<ChatMessage> getProductsOfBuyer(String buyerEmail);

    ChatMessage saveMessage(ChatMessage chatMessage) throws Exception;
    ChatMessage getAllMessage(String productId) throws Exception;
    ChatMessage getBuyerChat(String buyerEmailId, String productId) throws Exception;
    ChatMessage getChatById(String productId) throws Exception;

    List<BuyerChatWithProductId> getBuyersList(String sellerEmail);
}
