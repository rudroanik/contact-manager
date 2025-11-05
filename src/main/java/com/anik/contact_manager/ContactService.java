package com.anik.contact_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public  Contact addContact (Contact contact) {
        return contactRepository.save(contact);
    }
    public List<Contact> saveAllContact(List<Contact> contacts) {
        return contactRepository.saveAll(contacts);
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }
    public List<Contact> getContactsByName(String name) {
        return contactRepository.findByFirstNameIgnoreCase(name);
    }

    public long getInactiveContactsCount(boolean inactive) {
        return contactRepository.countByInactive(inactive);
    }
}
