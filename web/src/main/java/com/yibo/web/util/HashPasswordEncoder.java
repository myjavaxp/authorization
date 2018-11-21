package com.yibo.web.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class HashPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return "123456";
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return true;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return true;
    }
}