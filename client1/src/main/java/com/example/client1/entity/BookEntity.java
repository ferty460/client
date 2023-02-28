package com.example.client1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookEntity {
    public Long id;
    public String title;
    public String author;
    public String publishing;
    public int year;
    public String kind;
}
