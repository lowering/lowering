package io.github.lowering.security.web.controller;

import io.github.lowering.security.annotation.Current;
import io.github.lowering.security.doamin.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/19.
 */
@Controller
@RequestMapping("/authorize")
public class AuthorizationController {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public Principal me(@Current Principal principal){
        return principal;
    }
}
