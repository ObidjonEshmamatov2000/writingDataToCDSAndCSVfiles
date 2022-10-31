package com.epam.workingwithcdsfiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileContent {
    private UUID id;
    private byte[] content;
}
