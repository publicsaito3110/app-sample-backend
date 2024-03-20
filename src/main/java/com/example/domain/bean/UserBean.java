package com.example.domain.bean;

import com.example.domain.bean.common.CmnUserBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

    private List<CmnUserBean> userList = new ArrayList<>();

    public void addUser(CmnUserBean bean) {
        userList.add(bean);
    }
}
