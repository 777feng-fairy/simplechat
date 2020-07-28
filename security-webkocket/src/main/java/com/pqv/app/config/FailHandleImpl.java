package com.pqv.app.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pqv.app.result.ResultMessage;

@Component
public class FailHandleImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
       response.setContentType("application/json;charset=utf-8");
       ResultMessage<String> resultMessage = new ResultMessage<>();
       resultMessage.setMessage("请求失败");
       resultMessage.setStatus(400);
       response.getWriter().write(new ObjectMapper().writeValueAsString(resultMessage));
       response.getWriter().flush();
       response.getWriter().close();
        
    }

}
