package com.rakhman.accountsandtransfers.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "email не может быть пустым")
        @Email(message = "Некорректный формат email")
        @Size(max = 255)
        String email,

        @NotBlank(message = "пароль не может быть пустым")
        @Size(min = 8, max = 64, message = "Пароль должен быть от 8 до 64 символов")
        String password
) {
}
