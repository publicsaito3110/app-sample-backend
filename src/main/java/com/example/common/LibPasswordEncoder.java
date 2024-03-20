package com.example.common;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class LibPasswordEncoder implements PasswordEncoder {

    private final String PASSWORD_SALT = "Aj3k0jhGs3dL28kA";
    private final int PASSWORD_STRETCHING = 191;

    @Override
    public String encode(CharSequence rawPassword) {

        try {
            // 生パスワードにソルトを設定
            String saltPass = PASSWORD_SALT + rawPassword;

            // ハッシュコード⽣成
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digestByteArr = messageDigest.digest(saltPass.getBytes());

            // ハッシュ値を規定までストレッチング
            for (int i = 0; i < PASSWORD_STRETCHING; i++) {
                digestByteArr = messageDigest.digest(digestByteArr);
            }

            // ハッシュ化パスワードを文字列に変換
            return DatatypeConverter.printHexBinary(digestByteArr).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
