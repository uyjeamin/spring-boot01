package com.example.springboot01.domain.feed.domain;


import com.example.springboot01.domain.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed extends BaseTimeIdEntity {
    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 500)
    private String content;

    @NotNull
    private Integer views;

    @NotNull
    private Integer likeCounts;

    @NotNull
    private Integer unlikeCounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Builder
    public Feed(Integer views ,String title, String content1, Integer likeCounts, Integer unlikeCounts, User user) {
        this.title = title;
        this.content = content1;
        this.likeCounts = likeCounts;
        this.unlikeCounts = unlikeCounts;
        this.user = user;
        this.views = views;
    }

    public void modifyFeed(String title,String content) {
        this.title = title;
        this.content = content;
    }

    public void addViews(){
      this.views += 1;
    }

    public void minusLikeCount() {
        this.likeCounts -= 1;
    }

    public void minusUnLikeCount() {
        this.unlikeCounts -= 1;
    }

}
