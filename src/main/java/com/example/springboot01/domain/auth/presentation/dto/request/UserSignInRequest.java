package com.example.springboot01.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequest {

    @NotBlank(message = "account_id는 공백, Null이 불가합니다.")
    private String accountId;

    @NotBlank(message = "password는 공백, Null이 불가합니다.")
    private String password;
}
