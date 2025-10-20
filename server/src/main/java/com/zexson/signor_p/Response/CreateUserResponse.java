package com.zexson.signor_p.Response;

import com.zexson.signor_p.UserModule.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {
    private User user;
    private String token;

    public CreateUserResponse(User user, String token){
        this.user = user;
        this.token = token;
    }
}
