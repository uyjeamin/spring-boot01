package com.example.springboot01.domain.user.presentation.dto;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserSignUpService userSignUpService;
    private final QueryMyInfoService queryMyInfoService;
    private final QueryAnotherUserInfoService queryAnotherUserInfoService;
    private final UpdateUserInfoService updateUserInfoService;
    private final ChangePasswordService changePasswordService;
    private final UserWithdrawalService userWithdrawalService;
    private final QueryUserListService queryUserListService;

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid UserSignUpRequest request) {
        userSignUpService.signUp(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void modifyInfo(@RequestBody @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.modifyInfo(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        changePasswordService.changePassword(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteUser() {
        userWithdrawalService.deleteUser();
    }

    @GetMapping
    public QueryUserInfoResponse getMyInfo() {
        return queryMyInfoService.getMyInfo();
    }

    @GetMapping("/info")
    public QueryUserInfoResponse getAnotherInfo(QueryAnotherUserInfoRequest request) {
        return queryAnotherUserInfoService.execute(request);
    }

    @GetMapping("/all")
    public QueryUserListResponse getUserList(@RequestParam Integer key) {
        return queryUserListService.execute(key);
    }


}
