package io.github.lowering.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.github.lowering.common.view.json.JView;
import io.github.lowering.security.annotation.Current;
import io.github.lowering.security.doamin.Principal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/19.
 */


@RestController
@RequestMapping("/authorize")
@Api(tags = "lowering-authorize",description = "程序验证入口")
public class AuthorizationController {

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("登录的用户信息")
    @JsonView(JView.Normal.class)
    public ResponseEntity<Principal> me(@ApiParam(hidden = true) @Current Principal principal){
        return ResponseEntity.ok(principal);
    }
}
