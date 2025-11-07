package com.anik.contact_manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Contact>> getContactsByName(@RequestParam("term") String name) {
        return ResponseEntity.ok(contactService.getContactsByName(name));
    }
    @GetMapping("/count-inactive")
    public ResponseEntity<Long> getIsActiveContactsCount() {
        return ResponseEntity.ok(contactService.getIsActiveContactsCount(false));
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<ContactFirstNameEmail>> getContactsByCategory(@RequestParam("name") String category) {
        return ResponseEntity.ok(contactService.getContactByCategory(category));
    }

    @GetMapping("/active/page")
    public ResponseEntity<Page<ContactProjection>> getActiveContact(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(contactService.getAllContact(PageRequest.of(page, size, Sort.by("lastName").ascending())));
    }

    @DeleteMapping("/delete-by-prefix")
    public ResponseEntity<Contact> deleteContactByPrefix(@RequestParam String prefix) {
        contactService.deleteContactsByPrefix(prefix);
        return ResponseEntity.notFound().build();
    }
}
