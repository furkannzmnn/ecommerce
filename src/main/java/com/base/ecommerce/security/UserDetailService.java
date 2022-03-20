package com.base.ecommerce.security;

import com.base.ecommerce.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
         com.base.ecommerce.model.user.User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

         boolean enabled = true;
         boolean accountNonExpired = true;
         boolean credentialsNonExpired = true;
         boolean accountNonLocked = true;

         return new User(
                 user.getUsername(),
                 user.getPassword(),
                 enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                 grantedAuthorities(user.getRole())
         );

    }
    private static List<GrantedAuthority> grantedAuthorities (List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return grantedAuthorities;
    }
}
