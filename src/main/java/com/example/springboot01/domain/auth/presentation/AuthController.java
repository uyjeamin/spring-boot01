package com.example.springboot01.domain.auth.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final TokenService tokenService;
    private final ReissueService reissueService;
    private final LogoutService logoutService;

    @PostMapping("/token")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequest request, Role role) {
        return tokenService.signIn(request, role);
    }

    @PatchMapping("/token")
    public TokenResponse reissue(String refreshToken, Role role) {
        return reissueService.reissue(refreshToken, role);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/token")
    public void logout() {
        logoutService.execute();
    }

}
