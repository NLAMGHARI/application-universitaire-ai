package com.example.demo.Service;
import com.example.demo.Model.Message;// Assurez-vous d'avoir un repository
import com.example.demo.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository; // Injecter le repository

    // Méthode pour sauvegarder un message
    public Message saveMessage(Message message) {
        return messageRepository.save(message); // Utiliser le repository pour sauvegarder le message
    }
}