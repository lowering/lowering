package io.github.lowering.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/5/19.
 */
public class LoweringCaptchaAuthorizationFilter extends UsernamePasswordAuthenticationFilter{

    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";

    private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    private boolean postOnly = true;

    @Autowired
    private HttpSession session;

    public LoweringCaptchaAuthorizationFilter(){
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/authorize/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String valicode = obtainCaptcha(request);
        if(valicode==null){
            throw new BadCredentialsException("验证码错误,请重试!");
        }

        String username = this.obtainUsername(request);
        if(username==null){
            throw new BadCredentialsException("用户名或密码错误,请重试!");
        }

        String password = this.obtainPassword(request);
        if(password == null){
            throw new BadCredentialsException("用户名或密码错误,请重试!");
        }

        request.setAttribute(this.getUsernameParameter(), username);
        request.setAttribute(this.getPasswordParameter(), password);

        Object captcha = this.session.getAttribute("captcha");
        if(captcha==null){
            throw new BadCredentialsException("验证码错误,请重试!");
        }

        if(!valicode.equalsIgnoreCase(captcha.toString())){
            throw new BadCredentialsException("验证码错误,请重试!");
        }


        return super.attemptAuthentication(request, response);
    }

    protected String obtainCaptcha(HttpServletRequest request) {
        return request.getParameter(captchaParameter);
    }

    public String getCaptchaParameter() {
        return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
        Assert.hasText(captchaParameter,"Valicode parameter must not be empty or null");
        this.captchaParameter = captchaParameter;
    }
}
