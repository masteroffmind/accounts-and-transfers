package com.rakhman.accountsandtransfers.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * В отличие от Kotlin, где для наследования классов и реализации интерфейсов используется один
 * общий символ ":",  в Java class реализует (implements) интерфейс
 *
 * interface расширяет (extends) другой интерфейс(просто добавляет новые сигнатуры методов к уже существующим
 * Реализацию сам генериурет сам Spring Data JPA
 */
public interface UserRepository extends JpaRepository<User, Long> {
    //В отличие от Kotlin, тип возвращаемого значения стоит перед методом
    Optional<User> findByEmail(String email);
}
