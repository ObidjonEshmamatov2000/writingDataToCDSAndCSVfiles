package com.epam.workingwithcdsfiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private UUID id;
    private String name;
    private Long size;
    private LocalDateTime date;
    {
        id = UUID.randomUUID();
        date = LocalDateTime.now();
    }
}
