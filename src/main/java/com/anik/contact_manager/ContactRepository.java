package com.anik.contact_manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFirstNameIgnoreCase (String firstName);

    Long countByActive(boolean isActive);

    @Query("select c.firstName,c.email from Contact  c where c.category=:category")
    List<ContactFirstNameEmail> getContactByCategory(@Param("category") String category);

    Page<ContactProjection> findAllByActiveTrue(Pageable pageable);

    void deleteByPhoneNumberStartingWith(String prefix);


}
