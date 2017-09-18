package com.cemk.exp.sendMail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {

    private PasswordAuthentication authentication;

    public SMTPAuthenticator(String login, String password) {
        authentication = new PasswordAuthentication(login, password);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }
}