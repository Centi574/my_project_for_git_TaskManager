package com.example.project_hex_one.component;

import io.jsonwebtoken.Clock;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static io.jsonwebtoken.impl.TextCodec.BASE64;

@Component
public class JWTHelper {
    private final String secretKey;
    private final String issuer;
    private final Long expirationSec;
    private final Long clockSkewSec;
    private final Clock clock;

    public JWTHelper(@Value("${jwt.issuer:app}") final String issuer,
                     @Value("${jwt.expiration-sec:86400}") final Long expirationSec,
                     @Value("${jwt.clock-skew-sec:300}") final Long clockSkewSec,
                     @Value("${jwt.secret:secret}") final String secret) {
        this.secretKey = BASE64.encode(secret);
        this.issuer = issuer;
        this.expirationSec = expirationSec;
        this.clockSkewSec = clockSkewSec;
        this.clock = DefaultClock.INSTANCE;
    }
}
