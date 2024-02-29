package com.ngleanhvu.service;

import com.ngleanhvu.dto.LoginDTO;
import com.ngleanhvu.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
