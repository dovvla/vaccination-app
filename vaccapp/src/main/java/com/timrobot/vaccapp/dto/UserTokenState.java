package com.timrobot.vaccapp.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserTokenState {
    private String accessToken;

    public UserTokenState() {
        this.accessToken = null;
    }

    public UserTokenState(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
