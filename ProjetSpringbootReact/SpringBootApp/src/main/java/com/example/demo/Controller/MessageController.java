package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Service.MessageService; // Assurez-vous d'avoir un service pour gérer les messages
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService; // Injecter le service

    // Méthode POST pour créer un message
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
}
