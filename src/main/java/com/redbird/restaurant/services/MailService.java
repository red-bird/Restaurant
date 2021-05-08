package com.redbird.restaurant.services;

public interface MailService {
    public void send(String emailTo, String subject, String message);
}
