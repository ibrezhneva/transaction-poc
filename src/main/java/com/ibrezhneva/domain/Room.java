package com.ibrezhneva.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {
    @Id
    long id;
    String name;
}
