package com.example.WantedMarket.service;
import com.example.WantedMarket.domain.dto.CustomUserDetails;
import com.example.WantedMarket.domain.entity.User;
import com.example.WantedMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userData = userRepository.findByUsername(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(userData);
    }
}