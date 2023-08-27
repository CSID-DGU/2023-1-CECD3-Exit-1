package com.example.exitmedserver.user.auth;

import com.example.exitmedserver.user.entity.User;
import com.example.exitmedserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("load");
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
