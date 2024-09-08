package com.kod95.truckmanagmentsystem.security;

import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import com.kod95.truckmanagmentsystem.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        log.info("Loaded user: " + username + " with roles: " + user.getUserAuth());
        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(AuthorityUtils.createAuthorityList("ROLE_" + user.getUserAuth().name()))
                .build();
    }

}
