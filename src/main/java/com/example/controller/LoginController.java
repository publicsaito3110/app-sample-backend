package com.example.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        try {
            // DaoAuthenticationProviderを用いた認証を行う
            daoAuthenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getId(), loginForm.getPassword())
            );
            // JWTトークンの生成
            String token = JWT.create().withClaim("username",loginForm.getId())
                    .sign(Algorithm.HMAC256("__secret__"));
            // トークンをクライアントに返す
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("x-auth-token",token);
            return new ResponseEntity(httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
