package com.kod95.truckmanagmentsystem.security;


import com.kod95.truckmanagmentsystem.exception.ApplicationException;
import com.kod95.truckmanagmentsystem.model.admin.Users;
import com.kod95.truckmanagmentsystem.model.enums.Exceptions;
import com.kod95.truckmanagmentsystem.repository.UsersRepository;
import com.kod95.truckmanagmentsystem.utils.EncryptionUtils;
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
    private final EncryptionUtils encryptionUtils;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        System.out.println("Loaded user: " + username + " with roles: " + user.getUserAuth());

        try {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    encryptionUtils.decrypt(user.getPassword()),
                    AuthorityUtils.createAuthorityList(user.getUserAuth().toString())
            );
        } catch (Exception e) {
            throw new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION);
        }
    }

}

