package com.example.springboot01.domain.user.service;

import com.example.springboot01.domain.user.domain.User;
import com.example.springboot01.domain.user.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChangePasswordService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        User ser = userFacade.getCurrentUser();

        if(!passwordEncoder.matches(reqest.getOIdPassword(),user.getPasswor())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        user.changePassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
