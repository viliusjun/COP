package com.example.lab1.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@Getter @Setter
@JsonbPropertyOrder({"name", "lastname", "email", "books"})
public class AuthorRetrievalDTO {
    private String name;
    private String lastname;
    private String email;
    private List<BookDTO> books;
}
