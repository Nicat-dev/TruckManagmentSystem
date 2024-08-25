package com.kod95.truckmanagmentsystem.security;

import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import com.kod95.truckmanagmentsystem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        System.out.println("Loaded user: " + username + " with roles: " + user.getUserAuth());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),  // Use the hashed password directly
                AuthorityUtils.createAuthorityList("ROLE_" + user.getUserAuth().toString())
        );
    }
}
