package com.anik.contact_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public long getIsActiveContactsCount(boolean isActive) {
        return contactRepository.countByActive(isActive);
    }
    public List<ContactFirstNameEmail> getContactByCategory(String category) {
        return contactRepository.getContactByCategory(category);
    }
    public Page<ContactProjection> getAllContact(Pageable pageable) {
        return contactRepository.findAllByActiveTrue(pageable);
    }
    public void deleteContactsByPrefix(String prefix) {
         contactRepository.deleteByPhoneNumberStartingWith(prefix);
    }
}
