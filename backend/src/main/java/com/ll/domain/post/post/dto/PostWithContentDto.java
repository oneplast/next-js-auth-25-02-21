package com.ll.nextjs20250110.domain.post.post.dto;

import com.ll.nextjs20250110.domain.post.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
public class PostWithContentDto {
    @NonNull
    private final long id;

    @NonNull
    private final LocalDateTime createDate;

    @NonNull
    private final LocalDateTime modifyDate;

    @NonNull
    private final long authorId;

    @NonNull
    private final String authorName;

    @NonNull
    private final String title;

    @NonNull
    private final String content;

    @NonNull
    private final boolean published;

    @NonNull
    private final boolean listed;

    @Setter
    private Boolean actorCanModify;

    @Setter
    private Boolean actorCanDelete;

    public PostWithContentDto(Post post) {
        this.id = post.getId();
        this.createDate = post.getCreateDate();
        this.modifyDate = post.getModifyDate();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.published = post.isPublished();
        this.listed = post.isListed();
    }
}
