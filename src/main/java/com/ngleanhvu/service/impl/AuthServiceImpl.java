package com.ngleanhvu.service.impl;

import com.ngleanhvu.dto.LoginDTO;
import com.ngleanhvu.dto.RegisterDTO;
import com.ngleanhvu.entity.Role;
import com.ngleanhvu.entity.User;
import com.ngleanhvu.execption.BlogAPIException;
import com.ngleanhvu.repository.RoleRepository;
import com.ngleanhvu.repository.UserRepository;
import com.ngleanhvu.security.JwtTokenProvider;
import com.ngleanhvu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           RoleRepository roleRepository,
                           UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider){
        this.authenticationManager=authenticationManager;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.jwtTokenProvider=jwtTokenProvider;
    }
    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),
                loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
    @Override
    public String register(RegisterDTO registerDTO) {
        if(userRepository.existByUsername(registerDTO.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already existed");
        }
        if(userRepository.existByEmail(registerDTO.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already existed");
        }
        User user=new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        Set<Role> roles=new HashSet<>();
        Role role=roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "Register user successfully";
    }
}