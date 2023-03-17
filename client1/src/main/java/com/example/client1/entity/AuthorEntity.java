package com.example.client1.entity;

import lombok.Data;

import java.util.List;

@Data
public class AuthorEntity {
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private List<BookEntity> book;
}
