package com.example.application.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.application.data.Role;
import com.example.application.data.User;
import com.example.application.data.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("user");
            String password = "password";
            user.setHashedPassword(passwordEncoder.encode(password));
            Set<Role> set = new HashSet<Role>();
            set.add(Role.USER);
            user.setRoles(set);
            this.userRepository.save(user);
            User admin = new User();
            admin.setUsername("admin");
            password = "password";
            admin.setHashedPassword(passwordEncoder.encode(password));
            set = new HashSet<Role>();
            set.add(Role.USER);
            set.add(Role.ADMIN);
            admin.setRoles(set);
            this.userRepository.save(admin);

        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Käyttäjää ei ole saatavilla nimellä " + username));
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getHashedPassword(),
                getAuthorities(u));
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }



}
