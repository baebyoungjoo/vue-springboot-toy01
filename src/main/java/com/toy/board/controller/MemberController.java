package com.toy.board.controller;

import com.toy.board.domain.Member;
import com.toy.board.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:7732")
@Api(description = "회원가입 관련")
public class MemberController {

    @Autowired MemberRepository repository;

    @RequestMapping(value = "/check/{userId}", method = RequestMethod.POST)
    @ApiOperation(value = "중복 확인", notes = "회원 가입 시 ID 중복 확인")
    public ResponseEntity<String> memberCheck(@PathVariable("userId") String userId) {
        Optional<Member> optMember = repository.findByUserId(userId);

        if (optMember.isPresent()) {
            return new ResponseEntity<>("EXIST", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT_EXIST", HttpStatus.OK);
        }
    }

    private BCryptPasswordEncoder BCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/join", method = RequestMethod.PUT)
    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    public ResponseEntity<String> memberJoin(@RequestBody Member member) {

        Member _member = member;

        _member.setUserId(member.getUserId());
        _member.setName(member.getName());
        _member.setPassword(BCryptPasswordEncoder.encode(member.getPassword()));
        _member.setEmail(member.getEmail());

        repository.save(_member);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "로그인")
    public ResponseEntity<Member> memberLogin(@RequestBody Member member) {

        Optional<Member> optMemberData = repository.findByUserId(member.getUserId());

        if (optMemberData.isPresent()) {
            Member _member = optMemberData.get();
            if (BCryptPasswordEncoder.matches(member.getPassword(), _member.getPassword())) {
                /* TODO 비밀번호 일치 = 로그인 성공 */
            } else {
                /* TODO 비밀번호 불일치 = 로그인 실패 */
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
