package io.github.lowering.security.doamin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.github.lowering.common.view.json.JView;
import io.github.lowering.domain.User;
import io.github.lowering.security.core.DefaultGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/19.
 */
public class Principal implements UserDetails,Serializable{

    private User user;
    public Principal(User user){
        Assert.notNull(user,"用户不能为空!");
        this.user = user;
    }

    @Override
    @JsonView(JView.Normal.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.user.getRoles().forEach(role -> {
            authorities.add(new DefaultGrantedAuthority(role.getConstant()));
        });
        return authorities;
    }
    @JsonView(JView.Normal.class)
    public String getId(){
        return this.user.getId();
    }

    @Override
    @JsonView(JView.Private.class)
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    @JsonView(JView.Normal.class)
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getEnable();
    }

}
