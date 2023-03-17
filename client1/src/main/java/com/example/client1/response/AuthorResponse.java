package com.example.client1.response;

import com.example.client1.entity.AuthorEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponse extends BaseResponse {
    private AuthorEntity author;
    public AuthorResponse(boolean success, String message, AuthorEntity author) {
        super(success, message);
        this.author = author;
    }
    public AuthorResponse(AuthorEntity author) {
        super(true, "Author data");
    }
}
