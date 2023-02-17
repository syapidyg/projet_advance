package com.advance.pharmacie.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class GeneralUtil {

    public static String genererPasswordUser(String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        log.info("La chaine generer est {}", password);
        return bcrypt.encode(password);
    }

    public static boolean comparePassword(String clear, String crypt) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        return bcrypt.matches(clear, crypt);
    }
}
