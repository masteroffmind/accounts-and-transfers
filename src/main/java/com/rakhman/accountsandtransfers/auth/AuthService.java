package com.rakhman.accountsandtransfers.auth;

import com.rakhman.accountsandtransfers.user.User;
import com.rakhman.accountsandtransfers.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(RegisterRequest request){
        String passwordHash = passwordEncoder.encode(request.password());
        User user = new User(request.email(), passwordHash);
        userRepository.save(user);
    }
}
