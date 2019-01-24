package com.toy.board.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.toy.board.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	/* select x from Board b where b.title=? */
	List<Board> findByTitle(String title);
}
