package com.rakhman.accountsandtransfers.auth;


import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.exception.ConstraintViolationException;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/auth")
public class AuthController{
    private final AuthService authService;
    public AuthController (AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request){
        if ((request.password().getBytes(StandardCharsets.UTF_8).length) > 72){
            return ResponseEntity.badRequest().build();
        }
        try{
            authService.register(request);
            /*
             * Данное исключение бросится, если при попытке сохранить или изменить
             * данные в базе это может нарушить их целостность
             */
        }catch(DataIntegrityViolationException ex){
            for (Throwable cause = ex.getCause(); cause != null; cause = cause.getCause()){
                /*
                 * проверяет, является ли текущая причина исключением Hibernate о нарушении ограничения базы
                 * Если да, Java сразу дает переменную violation нужного типа
                 */
                if (cause instanceof ConstraintViolationException violation
                        && "users_email_key".equals(violation.getConstraintName())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
            throw ex;
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
