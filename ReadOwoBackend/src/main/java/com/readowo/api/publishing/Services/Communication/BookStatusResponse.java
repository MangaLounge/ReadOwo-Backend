package com.readowo.api.publishing.Services.Communication;

import com.readowo.api.Shared.BaseResponse;
import com.readowo.api.publishing.Models.BookStatus;

public class BookStatusResponse extends BaseResponse<BookStatus> {

    public BookStatusResponse(String message) {
        super(message);
    }

    public BookStatusResponse(BookStatus resource) {
        super(resource);
    }
}
