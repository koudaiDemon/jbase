package com.something.drools.fact;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/6  20:00
 */
public class Message {

    enum MessageStatus {
        /**
         *
         */
        HELLO,
        /**
         *
         */
        GOODBYE
    }

    private String message;
    private MessageStatus status;

    public Message() {
    }

    public Message(String message, MessageStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
