package com.toy.board.repository;

import org.springframework.data.repository.CrudRepository;

import com.toy.board.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

}
