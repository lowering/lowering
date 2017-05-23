package io.github.lowering.security.core;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.lowering.common.view.json.JView;
import io.github.lowering.security.doamin.Principal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * Created by Administrator on 2017/5/23.
 */
public class DefaultGrantedAuthority implements GrantedAuthority {

    @JsonView(JView.Normal.class)
    private String authority;

    public DefaultGrantedAuthority(String authority){
        Assert.hasText(authority,"权限字符串不能为空");
        this.authority=authority;
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultGrantedAuthority that = (DefaultGrantedAuthority) o;

        return authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }
}
