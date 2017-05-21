package io.github.lowering.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2017/5/19.
 */
@Configuration
public class SecurityConfiguration {

    private static final Log logger = LogFactory.getLog(MethodSecurityConfiguration.class);

    @PostConstruct
    public void init(){
        logger.info("初始化Security全局配置!");
    }

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration{

        @PostConstruct
        public void init(){
            logger.info("初始化全局方法安全配置!");
        }
    }

    @Configuration
    @EnableWebSecurity
    protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;


        @PostConstruct
        public void init(){
            logger.info("初始化Mvc安全配置...");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            super.configure(auth);
            auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
        }

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public LoweringCaptchaAuthorizationFilter loweringCaptchaAuthorizationFilter(){
            LoweringCaptchaAuthorizationFilter loweringCaptchaAuthorizationFilter = new LoweringCaptchaAuthorizationFilter();

            return loweringCaptchaAuthorizationFilter;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .exceptionHandling();

            http.headers().frameOptions().sameOrigin();

            http.authorizeRequests()
                    .antMatchers("/static/**","/error","/failure","/doc/**","/test/**").permitAll()
                    .anyRequest().authenticated();

            http.formLogin()
                    .loginPage("/login")
                    .permitAll();

            http.logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true);

            http.addFilterAt(loweringCaptchaAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

}
