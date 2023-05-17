package com.example.myblog.service;


import com.example.myblog.dto.BoardDto;
import com.example.myblog.model.Board;
import com.example.myblog.model.User;
import com.example.myblog.repository.BoardRepository;
import com.example.myblog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public Long write(BoardDto boardDto, User user) {
        boardDto.setUser(user);
        Board saveBoard = boardDto.toEntity();
        boardRepository.save(saveBoard);
        return saveBoard.getId();
    }

    @Transactional(readOnly = true)// 읽기전용 -> 상태변화X -> 영속성 컨택스트 관리 X
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Board> categoryList(Pageable pageable, String category) {
        return boardRepository.findByCategory(pageable, category);
    }


}
