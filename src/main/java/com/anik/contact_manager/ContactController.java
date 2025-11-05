package com.anik.contact_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> addContact(Contact contact) {
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @PostMapping("/mass")
    public ResponseEntity<List<Contact>> addContact(List<Contact> contact) {
        return ResponseEntity.ok(contactService.saveAllContact(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactsById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Contact>> getContactsByName(@RequestParam String name) {
        return ResponseEntity.ok(contactService.getContactsByName(name));
    }
    @GetMapping("/count-inactive")
    public ResponseEntity<Long> getInactiveContactsCount() {
        return ResponseEntity.ok(contactService.getInactiveContactsCount(true));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<Contact>> getContactsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/active/page")
    public ResponseEntity<List<Contact>> getActiveContact() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @DeleteMapping("/delete-by-prefix")
    public ResponseEntity<Contact> deleteContactByPrefix(@RequestParam String prefix) {
        return ResponseEntity.notFound().build();
    }
}
