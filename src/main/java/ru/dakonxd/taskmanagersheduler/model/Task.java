package ru.dakonxd.taskmanagersheduler.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "is_active")
    boolean isActive;

    @Column(name = "modified")
    LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User owner;

}
