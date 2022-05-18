package com.springbootblogrestapi.payload;

public class JWTAuthResponse {

    private String accessToken;
    private String TokenType="Bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return TokenType;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        TokenType = tokenType;
    }
}
