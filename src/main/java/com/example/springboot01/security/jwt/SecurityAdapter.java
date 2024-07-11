package com.example.springboot01.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CompanyMapper companyMapper;
    private final studentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final UserMapper userMapper;
    private final UserJPaRepository userJPaRepository;
    @Value("${auth-code}")
    private String authCode;

    @Override
    public Long getCurrentUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
    @Override
    public Authority get CurrentUserAuthority() {
        UserDetails currenUser = (UserDetails) getCurrentUserDetails();
        return Authority.valueOf(currenUser.getAuthorities().iterator().next().getAuthority());

    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public Company getCurrentCompany() {
        CompanyDetails companyDetails = (CompanyDetails) getCurrentUserDetails();
        return companyMapper.toDomain(companyDetails.getCompany());
    }

    @Override
    public Student getCurrentStudent() {
        StudentDetails studentDetails = (StudentDetails) getCurrentUserDetails();
        return studentMapper.toDomain(studentDetails.getStudent());
    }

    @Override
    public User getCurrentUser() {
        Long currentUserId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        return userJpaRepository.findById(currentUserId)
                .map(userMapper::toDomain)
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }

    @Override
    public String getServerAuthCode() {
        return authCode;
    }

    private Object getCurrentUserDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
