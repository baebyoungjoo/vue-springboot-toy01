package com.toy.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toy.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	/* select x from Board b where b.title=? */
	List<Board> findByTitle(String title);
}
