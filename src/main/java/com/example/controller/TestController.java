package com.example.controller;

import com.example.domain.bean.TestGetBean;
import com.example.domain.bean.TestPostBean;
import com.example.form.TestPostForm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {


    @RequestMapping(value = "/test/get", method = RequestMethod.GET)
    public TestGetBean testGet() {
        return new TestGetBean("Request GET 接続完了");
    }

    @RequestMapping(value = "/test/post", method = RequestMethod.POST)
    public TestPostBean testPost(@RequestBody TestPostForm testPostForm) {
        return new TestPostBean("Request POST 接続完了 パラメーターは" + testPostForm.getParam());
    }
}
