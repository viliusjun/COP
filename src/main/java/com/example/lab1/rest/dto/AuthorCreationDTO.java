package com.example.lab1.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthorCreationDTO {
    private String name;
    private String lastname;
    private String email;
}
