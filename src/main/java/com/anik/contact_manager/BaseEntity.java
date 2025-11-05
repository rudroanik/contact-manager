package com.anik.contact_manager;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    Long id;

    @PrePersist
    private void prePersist() {
        if (this instanceof Auditable auditable) {
            LocalDateTime now = LocalDateTime.now();
            if (auditable.getCreatedBy() == null) {
                auditable.setCreatedBy(now);
            }
        }
    }
}
