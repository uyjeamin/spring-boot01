package com.example.springboot01.domain.user.domain;

import com.example.springboot01.domain.user.domain.type.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseIdEntity {

    @NotNull
    @Size(max = 64)
    @Column(unique = true)
    private String email;


    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String accounId;


    @NotNull
    @Size(max = 64)
    private String password;

    @NotNull
    @Size(max = 10)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private Integer followCounts;

    @Size(max = 50)
    private String introduce;

    @Builder
    public User(String email, String accounId, String password, String name, Sex sex, Role role, Integer followCounts, String introduce) {
        this.email = email;
        this.accounId = accounId;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.role = role;
        this.followCounts = followCounts;
        this.introduce = introduce;
    }

    public void modifyInfo(String name) {
        this.name = name;
        this.introduce = introduce;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void addFollower(){
        this.followCounts += 1;
    }

}
