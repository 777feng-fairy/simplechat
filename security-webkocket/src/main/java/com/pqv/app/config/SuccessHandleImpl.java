package com.pqv.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pqv.app.bean.User;
import com.pqv.app.result.ResultMessage;

@Component
public class SuccessHandleImpl implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResultMessage<User> resultMessage = new ResultMessage<>(); 
        resultMessage.setData((User)authentication.getPrincipal());
        resultMessage.setStatus(200);
        response.getWriter().write(new ObjectMapper().writeValueAsString(resultMessage));
        response.getWriter().flush();
        response.getWriter().close();
    }

}
