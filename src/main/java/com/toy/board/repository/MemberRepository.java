package com.toy.board.repository;

import com.toy.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	/* select x from Member b where b.userId=? */
//	@Where(clause = "binary")
//	@Query("select m from Member m where m.userId=binary ?1")
	Optional<Member> findByUserId(String userId);
}
