package com.example.myblog.repository;
import com.example.myblog.model.Board;
import com.example.myblog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository{
    List<Board> findAllByUserOrderByIdDesc(User user);

    @Modifying
    @Query("update Board p set p.views = p.views + 1 where p.id = :id")
    int updateViews(@Param("id")Long id);


    Page<Board> findByCategory(Pageable pageable, String category);
}
