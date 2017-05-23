package io.github.lowering.security.handler;

import io.github.lowering.security.doamin.Principal;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/22.
 */
@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler,AuthenticationFailureHandler,LogoutSuccessHandler,AccessDeniedHandler,AuthenticationEntryPoint {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //处理登录失败
        this.handler(request,response,500,exception);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //处理登录成功
        Principal principal = (Principal) authentication.getPrincipal();
        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        writer.write(String.format("{\"msg\":\"%s\"}",principal.getId()));
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //处理401错误
        this.handler(request,response,401,authException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //处理403错误
        this.handler(request,response,403,accessDeniedException);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }

    protected void handler(HttpServletRequest request, HttpServletResponse response, int httpStatus, Exception exception) throws IOException,ServletException{
        request.setAttribute("javax.servlet.error.exception", exception);
        request.setAttribute("javax.servlet.error.status_code", httpStatus);
        request.setAttribute("javax.servlet.error.message", exception.getMessage());
        request.getRequestDispatcher("/error").forward(request, response);
    }
}
