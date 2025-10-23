package com.zexson.signor_p.DataObjectTransferClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DtoIUUser {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
