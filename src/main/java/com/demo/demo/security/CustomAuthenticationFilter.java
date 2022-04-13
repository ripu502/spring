package com.demo.demo.security;

import com.demo.demo.dto.LoginDTO;
import com.demo.demo.exception.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils)
    {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws  AuthenticationException{
        try
        {
            String in = request.getReader().lines().reduce("", String::concat);
            LoginDTO req = new ObjectMapper().readValue(in, LoginDTO.class);
            String username = req.getUsername();
            String password = req.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Bad Request, or illegal Request");
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            } catch (IOException ex) {
                ex.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User springUser = (User) authentication.getPrincipal();
        String access_token = jwtUtils.createAccessToken(springUser, request.getRequestURL().toString());
        String refresh_token = jwtUtils.createRefreshToken(springUser, request.getRequestURL().toString());
        Map<String, String> tokenResponse = new HashMap<>();
        tokenResponse.put("access_token", access_token);
        tokenResponse.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokenResponse);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.print(failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);
    }


}
