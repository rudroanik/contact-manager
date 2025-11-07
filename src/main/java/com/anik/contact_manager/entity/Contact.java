package com.anik.contact_manager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
public class Contact extends BaseEntity implements Auditable {

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("phone_number")
    String phoneNumber;
    String email;
    boolean isActive;
    String category;
    LocalDateTime creationDate;
}
