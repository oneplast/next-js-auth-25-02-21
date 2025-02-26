package com.ll.domain.member.member.service;

import com.ll.domain.member.member.entity.Member;
import com.ll.domain.member.member.repository.MemberRepository;
import com.ll.global.exceptions.ServiceException;
import com.ll.global.search.MemberSearchKeywordTypeV1;
import com.ll.util.Ut;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthTokenService authTokenService;
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        findByUsername(username)
                .ifPresent(__ -> {
                    throw new IllegalArgumentException(
                            new ServiceException("409-1", "해당 username은 이미 사용중입니다."));
                });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .apiKey(UUID.randomUUID().toString())
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(long authorId) {
        return memberRepository.findById(authorId);
    }

    public Optional<Member> findByApiKey(String apiKey) {
        return memberRepository.findByApiKey(apiKey);
    }

    public String genAccessToken(Member member) {
        return authTokenService.genAccessToken(member);
    }

    public String genAuthToken(Member member) {
        return member.getApiKey() + " " + this.genAccessToken(member);
    }

    public Member getMemberFromAccessToken(String accessToken) {
        Map<String, Object> payload = authTokenService.payload(accessToken);

        if (payload == null) {
            return null;
        }

        long id = (long) payload.get("id");
        String username = (String) payload.get("username");
        String nickname = (String) payload.get("nickname");

        return new Member(id, username, nickname);
    }

    public Page<Member> findByPaged(MemberSearchKeywordTypeV1 searchKeywordType, String searchKeyword, int page,
                                    int pageSize) {
        if (Ut.str.isBlank(searchKeyword)) {
            return findByPaged(page, pageSize);
        }

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        searchKeyword = "%" + searchKeyword + "%";

        return switch (searchKeywordType) {
            case MemberSearchKeywordTypeV1.username -> memberRepository.findByUsernameLike(searchKeyword, pageRequest);
            default -> memberRepository.findByNicknameLike(searchKeyword, pageRequest);
        };
    }

    public Page<Member> findByPaged(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));

        return memberRepository.findAll(pageRequest);
    }

    public void modify(Member member, @NotBlank String nickname) {
        member.setNickname(nickname);
    }
}
