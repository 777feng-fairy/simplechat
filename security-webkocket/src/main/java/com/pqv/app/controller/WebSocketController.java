package com.pqv.app.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.pqv.app.result.ChatMessage;


@Controller
public class WebSocketController {
   
   /**
    * @MessageMapping 和  @RequestMapping作用类似
    * @SendTo() 把当前方法的返回值返回给指定的消息队列
    * @param chatMessage
    * @return
    */   
   @MessageMapping("/api/user/wechat")
   @SendTo("/topic/groupchat")
   public ChatMessage groupChat(ChatMessage chatMessage) {
       return chatMessage;
   }
   
    
   
    
}
