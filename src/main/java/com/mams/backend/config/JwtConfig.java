package com.mams.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private String secret = "your_default_very_long_secret_key_for_jwt_token_signing";
    private long expirationTime = 864000000; // 10 days
}