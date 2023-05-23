package com.example.lab1.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@Getter
@Setter
@JsonbPropertyOrder({"id", "title", "genres"})
public class BookDTO {
    private Long id;
    private String title;

    private List<String> genres;
}
