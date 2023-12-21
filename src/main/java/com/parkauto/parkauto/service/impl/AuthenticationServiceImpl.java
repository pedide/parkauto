package com.parkauto.parkauto.service.impl;

import com.parkauto.parkauto.dto.JwtAuthenticationResponse;
import com.parkauto.parkauto.dto.RefreshTokenRequest;
import com.parkauto.parkauto.dto.SignInRequest;
import com.parkauto.parkauto.dto.SignUpRequest;
import com.parkauto.parkauto.entity.Role;
import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.repository.IUserRepository;
import com.parkauto.parkauto.service.AuthenticationService;
import com.parkauto.parkauto.service.EmailService;
import com.parkauto.parkauto.service.JWTService;
import jakarta.jws.soap.SOAPBinding;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    public EmailService emailService;

    public User signup(SignUpRequest signUpRequest) {

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        user.setRole(Role.ROLE_USER);  //User is not allow to be an admin in register
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        LOGGER.info("Votre mot de passe"+ signUpRequest.getPassword());

        emailService.sendConfirmRegister(signUpRequest.getEmail(),
                signUpRequest.getFirstName(),signUpRequest.getPassword());
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(), signInRequest.getPassword()
                ));

        var user = userRepository.findUserByEmail(signInRequest.getEmail());

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setEmail(user.getEmail());
        jwtAuthenticationResponse.setFirstName(user.getFirstname());
        jwtAuthenticationResponse.setLastName(user.getLastname());
        jwtAuthenticationResponse.setProfileImageURL(user.getProfileImageURL());
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findUserByEmail(userEmail);

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;

        }
        return null;
    }
}
