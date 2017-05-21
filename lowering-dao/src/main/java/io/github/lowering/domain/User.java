package io.github.lowering.domain;


import io.github.lowering.common.domain.Id;

/**
 * Created by Administrator on 2017/5/19.
 */
public class User extends Id {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
