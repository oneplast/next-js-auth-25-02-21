package com.ll.domain.member.member.repository;

import com.ll.domain.member.member.entity.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Optional<Member> findByApiKey(String apiKey);

    Page<Member> findByUsernameLike(String usernameLike, PageRequest pageRequest);

    Page<Member> findByNicknameLike(String nicknameLike, PageRequest pageRequest);
}
