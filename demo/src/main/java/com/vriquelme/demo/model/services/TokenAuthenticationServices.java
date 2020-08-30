package com.vriquelme.demo.model.services;

import org.springframework.stereotype.Service;

@Service
public interface TokenAuthenticationServices {
    String authorize(String email);
}
