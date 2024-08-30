package com.sparta.upgradeschedulemanagement.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String userName;
    private String email;
    private String password;

}
