package com.cccloud.blogs.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 作者：徐卫超
 * 时间：2020/6/5 10:40
 * 描述：
 */
public class IUser extends User {

    public IUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
