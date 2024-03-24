package com.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthorizeFilter extends OncePerRequestFilter {

    private final AntPathRequestMatcher matcher = new AntPathRequestMatcher("/api/login");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // ヘッダーのキーからトークンを取得
        String xAuthToken = request.getHeader("X-AUTH-TOKEN");

        if (xAuthToken == null || !xAuthToken.startsWith("Bearer ")) {
            // 未認証のトークンのとき、何もせずクライアントへリターン
            filterChain.doFilter(request, response);
            return;
        } else if (matcher.matches(request)) {
            // ログインURIのとき、何もせずクライアントへリターン
            filterChain.doFilter(request, response);
            return;
        }

        // トークンの検証
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("__secret__")).build().verify(xAuthToken.substring(7));
        Date nowDate = new Date();
        Date expiresDate = decodedJWT.getExpiresAt();

        // トークンの有効期限が過ぎているとき
        if (nowDate.after(expiresDate)) {
            System.out.println("No!!!!!!");
        }

        // usernameの取得
        String username = decodedJWT.getClaim("username").toString();
        // ログイン状態の設定
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));

        //---------------------------------
        // JWTの有効期限の処理はどうするべきか?
        //---------------------------------

        filterChain.doFilter(request, response);
    }
}
