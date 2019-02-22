package com.toy.board.repository;

import com.toy.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	/* select x from Member b where b.userId=? */
	Optional<Member> findByUserId(String userId);
}
