package com.ll.global.security;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    @Getter
    private long id;
    @Getter
    private String nickname;

    public SecurityUser(long id,
                        String username,
                        String password,
                        String nickname,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.nickname = nickname;
    }
}
