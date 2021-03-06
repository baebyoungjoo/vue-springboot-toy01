package com.toy.board.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.toy.board.domain.Board;
import com.toy.board.repository.BoardRepository;

@RestController
@RequestMapping("/api")
/* 로컬 개발 주석 해제 */
@CrossOrigin(origins = "http://localhost:7732")
@Api(description = "게시글 관련")
public class BoardController {

	@Autowired BoardRepository repository;
	@Autowired XssFilter xssFilter;
	
	@RequestMapping(value = "/board", method = RequestMethod.POST)
	@ApiOperation(value = "등록", notes = "새로운 게시글 등록")
	public Board newPostBoard(@RequestBody Board board) {
			Board _board = board;

			_board.setTitle(xssFilter.doFilter(board.getTitle()));
			_board.setText(xssFilter.doFilter(board.getText()));

			_board = repository.save(board);

			return _board;
	}
	
	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	@ApiOperation(value = "전체 보기", notes = "전체 게시글 보기")
	public List<Board> getAllBoard() {
		List<Board> boards = new ArrayList<>();
		repository.findAll().forEach(boards::add);
		
		return boards;
	}
	
	@RequestMapping(value = "/board/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "검색", notes = "특정 게시글 검색")
	public List<Board> findBoard(@PathVariable("id") long id) {
		Optional<Board> optBoard = repository.findById(id);
		List<Board> board = new ArrayList<>();
		
		/* ifPresent: 해당 객체가 null이 아닐 때 수행. 해당 객체가 null이면 아무 동작 하지 않음. */
		optBoard.ifPresent(board::add);
		
		return board;
	}
	
	@RequestMapping(value = "/board/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "삭제", notes = "게시글 삭제")
	public ResponseEntity<String> deleteBoard(@PathVariable("id") long id) {
		repository.deleteById(id);
		
		return new ResponseEntity<>("Board has been deleted!!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/board/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "수정", notes = "게시글 수정")
	public ResponseEntity<Board> updateBoard(@PathVariable("id") long id, @RequestBody(required=false) Board board) {
		/* Optional prefix: maybe or opt */
		Optional<Board> optBoardData = repository.findById(id);
		
		/* isPresent: 내부 객체가 null인지 아닌지 검사. null return: false */
		if (optBoardData.isPresent()) {
			Board _board = optBoardData.get();
			if (board != null) {
				_board.setUpdatedOn(ZonedDateTime.now());
				_board.setText(xssFilter.doFilter(board.getText()));
				_board.setTitle(xssFilter.doFilter(board.getTitle()));
			} else {
				_board.setHit(_board.getHit() + 1);
			}
			return new ResponseEntity<>(repository.save(_board), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
