package com.rakhman.accountsandtransfers.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     Обязательный пустой конструктор для Hibernate должен быть protected, чтобы случайно не вызвать в коде
     */
    protected User(){

    }
    //Основной конструктор для создания нового пользователя перед сохранением в БД
    public User(String email, String passwordHash){
        /**
         * поле id нет в основном конструкторе, так как бд сама его генерирует
         * GenerationType.IDENTITY
         */
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
