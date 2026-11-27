package com.rakhman.accountsandtransfers.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
/**
 * Помечает класс как конфигурацию Spring
 */
public class PasswordConfig {
    @Bean
    /**
     * Говорит Spring вызвать метод и зарегистрировать возвращенный объект в своем контексте.
     */
    public PasswordEncoder passwordEncoder(){
        /**
         * Объявляем метод с возращаемым типо PasswordEncoder
         */
        return new BCryptPasswordEncoder();
    }
}
