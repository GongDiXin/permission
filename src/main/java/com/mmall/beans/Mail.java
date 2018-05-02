package com.mmall.beans;

import java.util.Set;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/5/2 0:09
 */
public class Mail {

    private String subject;

    private String message;

    private String receiver;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Mail() {

    }

    public Mail(String subject, String message, String receiver) {
        this.subject = subject;
        this.message = message;
        this.receiver = receiver;
    }
}
