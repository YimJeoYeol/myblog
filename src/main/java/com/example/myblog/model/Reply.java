package com.example.myblog.model;

import com.example.myblog.dto.ReplyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 400)
    private String content;

    @ManyToOne                                              //연관관계 형성
    @JoinColumn(name = "boardId")
    @JsonIgnore
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)                      //연관관계 형성
    @JoinColumn(name = "userId")
    private User user;

    public ReplyDto toDto(){
        return ReplyDto.builder()
                .id(id)
                .content(content)
                .board(board)
                .user(user)
                .build();
    }
}
