package ru.dakonxd.taskmanagersheduler.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    private String recipientAddress;
    private String title;
    private String text;
}
