package com.zexson.signor_p.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserCreateDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
