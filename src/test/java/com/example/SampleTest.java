package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SampleTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Test01")
    public  void test01() {
        String hashPass = passwordEncoder.encode("1234");
        assertEquals("fe2154ed7548acd9a9a3de891c256de6537baec35d886b430bdf3b4bcd6b7e3e", hashPass);
        assertEquals(200, HttpStatus.OK.value());
    }
}
