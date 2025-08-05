package com.learn;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class TokenService {

    public String generateToken(){
        return Jwt.issuer("jwt-token")
                .subject("course")
                .groups("teacher")
//                .expiresIn(3600L)
                .expiresAt(System.currentTimeMillis()+3600)
                .sign();
    }
}
