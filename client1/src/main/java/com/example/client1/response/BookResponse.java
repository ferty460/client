package com.example.client1.response;

import com.example.client1.entity.BookEntity;
import lombok.Data;

@Data
public class BookResponse extends BaseResponse{
    private BookEntity data;

    public BookResponse(boolean success, String message, BookEntity data) {
        super(success, message);
        this.data = data;
    }
}
