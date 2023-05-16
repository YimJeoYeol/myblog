package com.example.myblog.model;

import com.example.myblog.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import java.util.List;
import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String category;

    @Lob
    private String content;

    @Column(nullable = false)
    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @OneToMany(mappedBy = "board",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replyList;

    public void updateBoard(String title, String content, String category){
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public BoardDto toDto(){
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .views(views)
                .user(user)
                .replyList(replyList)
                .build();
    }




}
