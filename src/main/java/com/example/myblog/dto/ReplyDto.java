package com.example.myblog.dto;

import com.example.myblog.model.Board;
import com.example.myblog.model.Reply;
import com.example.myblog.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.boot.MetadataBuilder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private Long id;
    private String content;
    private Board board;
    private User user;



    //request 용도
    public Reply toEntity(){
        return Reply.builder()
                .id(id)
                .content(content)
                .board(board)
                .user(user)
                .build();
    }

    //response 용도
    public ReplyDto toDto(Reply reply){
        this.id      = reply.getId();
        this.content = reply.getContent();
        this.board   = reply.getBoard();
        this.user    = reply.getUser();

        return this;
    }
}
