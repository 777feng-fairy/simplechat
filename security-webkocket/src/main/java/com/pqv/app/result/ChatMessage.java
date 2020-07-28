package com.pqv.app.result;




import lombok.Data;

@Data
public class ChatMessage {
    private String from;
    private String to;
    private String message;
    private String time;
}
