package com.example.controller;

import com.example.domain.bean.UserBean;
import com.example.domain.bean.common.CmnUserBean;
import com.example.form.UserForm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserBean user(@RequestBody UserForm userForm) {
        System.out.println("Pass");
        UserBean bean = new UserBean();
        bean.addUser(new CmnUserBean("A001", "田中"));
        bean.addUser(new CmnUserBean("A002", "鈴木"));
        return bean;
    }
}
