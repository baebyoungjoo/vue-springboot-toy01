package com.toy.board.controller;

import com.toy.board.domain.Member;
import com.toy.board.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@Api(description = "회원가입 관련")
public class MemberController {

    @Autowired MemberRepository repository;

    @RequestMapping(value = "/check/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "중복 확인", notes = "회원 가입 시 ID 중복 확인")
    public String memberCheck(@PathVariable("userId") String userId) {
        Optional<Member> optMember = repository.findByUserId(userId);

        if (optMember.isPresent()) {
            return "중복 아이디 있음";
        } else {
            return "중복 아이디 없음";
        }
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ApiOperation(value = "회원 가입", notes = "회원 가입")
    public String memberJoin() {
        return "join";
    }
}
