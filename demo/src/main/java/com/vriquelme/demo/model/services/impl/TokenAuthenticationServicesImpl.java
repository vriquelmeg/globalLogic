package com.vriquelme.demo.model.services.impl;

import com.vriquelme.demo.model.services.TokenAuthenticationServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenAuthenticationServicesImpl implements TokenAuthenticationServices {

    private final String token;

    public TokenAuthenticationServicesImpl(String p_token) {
        this.token = p_token;
    }

    @Override
    public String authorize(String email) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder().setSubject(email).signWith(key).compact();
    }

}
