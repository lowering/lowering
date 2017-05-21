package io.github.lowering.security.service;

import io.github.lowering.domain.User;
import io.github.lowering.repository.UserRepository;
import io.github.lowering.security.doamin.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service
@Transactional(readOnly = true)
public class LoweringUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)){
            throw new IllegalArgumentException("用户名不能为空!");
        }
        User user = this.userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(String.format("%s不存在!",username));
        }

        return new Principal(user);
    }
}
