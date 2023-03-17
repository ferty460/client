package com.example.client1.response;

import com.example.client1.entity.AuthorEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorListResponse extends BaseResponse {
    private Iterable<AuthorEntity> data;
    public AuthorListResponse(Iterable<AuthorEntity> data) {
        super(true, "Authors");
        this.data = data;
    }
}
