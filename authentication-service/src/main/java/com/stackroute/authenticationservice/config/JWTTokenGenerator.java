package com.stackroute.authenticationservice.config;

import com.stackroute.authenticationservice.domain.User;

import java.util.Map;

public interface JWTTokenGenerator {
    Map<String, String> generateToken(User user);

}
