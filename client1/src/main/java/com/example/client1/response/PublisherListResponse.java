package com.example.client1.response;

import com.example.client1.entity.PublisherEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PublisherListResponse extends BaseResponse{
    private Iterable<PublisherEntity> data;
    public PublisherListResponse(Iterable<PublisherEntity> data) {
        super(true, "Издательства");
        this.data = data;
    }
}
