package com.anik.contact_manager;

import java.time.LocalDateTime;

public interface Auditable {
    void setCreationDate(LocalDateTime creationDate);
    LocalDateTime getCreationDate();
}
