package com.ngleanhvu.security;

import com.ngleanhvu.entity.User;
import com.ngleanhvu.execption.ResourceNotFoundException;
import com.ngleanhvu.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository
                .findByUsernameOrEmail(username,username)
                .orElseThrow(()->new ResourceNotFoundException("User","username",username));
        Set<GrantedAuthority> authoritySet=user.getRoles()
                .stream().map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authoritySet);
    }
}
