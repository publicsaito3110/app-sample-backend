package com.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ユーザ検索 + 該当ユーザが存在しないときの処理
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException(username + " is not found");
//        }

        // ユーザの権限の追加
        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        // ユーザ情報から認証
        return new User(username, "fe2154ed7548acd9a9a3de891c256de6537baec35d886b430bdf3b4bcd6b7e3e", grantedAuthorities);
    }
}
