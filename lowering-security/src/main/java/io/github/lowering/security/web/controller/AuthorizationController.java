package io.github.lowering.security.web.controller;

import io.github.lowering.security.annotation.Current;
import io.github.lowering.security.doamin.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/19.
 */


@RestController
@RequestMapping("/authorize")
public class AuthorizationController {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Principal> me(@Current Principal principal){
        return ResponseEntity.ok(principal);
    }
}
