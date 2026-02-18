package com.example.Shopping.website.Service;

import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.ContactMessage;
import com.example.Shopping.website.Repositry.ContactMessageRepository;

@Service
public class ContactMessageService {
    private final ContactMessageRepository repository;

    public ContactMessageService(ContactMessageRepository repository) {
        this.repository = repository;
    }

    public ContactMessage save(ContactMessage msg) {
        return repository.save(msg);
    }
}


