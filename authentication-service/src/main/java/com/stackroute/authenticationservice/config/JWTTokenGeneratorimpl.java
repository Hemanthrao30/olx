package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTTokenGeneratorimpl implements JWTTokenGenerator {

    @Value("$(jwt.secret)")
    private String secret;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken = "";
        jwtToken = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put( "token", jwtToken);
        jwtTokenMap.put( "message", "Authentication Successful");

        return jwtTokenMap;
    }
}
