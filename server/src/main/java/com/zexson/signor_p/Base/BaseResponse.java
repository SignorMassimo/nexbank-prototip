package com.zexson.signor_p.Base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private Object data;
    private boolean success;
    private String message;

    public BaseResponse(Object data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }
}
