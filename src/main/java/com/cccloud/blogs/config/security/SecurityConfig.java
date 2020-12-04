package com.cccloud.blogs.config.security;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.utils.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    String[] patterns = new String[]{
            "/swagger-ui.html",
            "/editor/config",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/oauth/**",
            "/login/**",
            "/logout/**",
            "/authorization/**",
            "/open/**",
            "/doc.html"
    };



    private boolean dev = true;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().authorizeRequests()
                .antMatchers(patterns).permitAll()
                .anyRequest().authenticated().and().csrf().disable();
        //http.addFilterAt(new CustomJsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin().loginPage("/swagger-ui.html")
                .loginProcessingUrl("/authorization/login")
                .successHandler((request, response, authentication) -> {
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    JsonMessage<Object> json = JsonMessage.succeed("登录成功");
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(json));
                })
                .failureHandler((request, response, exception) -> {
                    //登录失败
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    JsonMessage<Object> json = JsonMessage.failed("登录失败");
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(json));
                }).and()
                .logout().
                logoutUrl("/authorization/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    JsonMessage<Object> json = JsonMessage.succeed("退出登录成功");
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(json));
                }).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                //.and().rememberMe().rememberMeServices(rememberMeServices()).tokenValiditySeconds(Constants.REMEMBER_LOGIN_TIME)
                //异常处理
                .and().exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    //未登录
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    JsonMessage<Object> json = JsonMessage.failed("访问受限", JsonMessage.NOT_PRIVILEGE, null);
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(json));
                })
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    JsonMessage<Object> json = JsonMessage.failed("登录失效请重新登录", JsonMessage.NOT_AUTHEN, null);
                    ObjectMapper objectMapper = new ObjectMapper();
                    response.getWriter().write(objectMapper.writeValueAsString(json));
                });
    }


}
