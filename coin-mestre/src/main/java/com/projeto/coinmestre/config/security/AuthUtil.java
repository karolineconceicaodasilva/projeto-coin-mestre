package com.projeto.coinmestre.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

    public static Long getUserId() {
        try {
            return ((SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getUserName() {
        if (SecurityContextHolder.getContext() == null
                || SecurityContextHolder.getContext().getAuthentication() == null
                || "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
