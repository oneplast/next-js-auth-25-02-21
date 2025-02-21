package com.ll.nextjs20250110.domain.member.member.dto;

import com.ll.nextjs20250110.domain.member.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class MemberDto {
    @NonNull
    private final long id;

    @NonNull
    private final LocalDateTime createDate;

    @NonNull
    private final LocalDateTime modifyDate;

    @NonNull
    private final String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.createDate = member.getCreateDate();
        this.modifyDate = member.getModifyDate();
        this.nickname = member.getNickname();
    }
}
