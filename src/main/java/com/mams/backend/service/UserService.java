//package com.mams.backend.service;
//
//import com.mams.backend.dto.AuthenticationRequest;
//import com.mams.backend.dto.AuthenticationResponse;
//import com.mams.backend.dto.RegisterRequest;
//import com.mams.backend.entity.Location;
//import com.mams.backend.entity.User;
//import com.mams.backend.repository.LocationRepository;
//import com.mams.backend.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final LocationRepository locationRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//    }
//
//    public AuthenticationResponse register(RegisterRequest request) {
//        Location assignedBase = null;
//        if (request.getAssignedBaseId() != null) {
//            assignedBase = locationRepository.findById(request.getAssignedBaseId())
//                    .orElseThrow(() -> new IllegalArgumentException("Base not found"));
//        }
//
//        User user = User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .fullName(request.getFullName())
//                .role(request.getRole())
//                .assignedBase(assignedBase)
//                .build();
//
//        userRepository.save(user);
//        String token = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        String token = jwtService.generateToken(user);
//
//        return AuthenticationResponse.builder()
//                .token(token)
//                .build();
//    }
//}

package com.mams.backend.service;

import com.mams.backend.dto.AuthenticationRequest;
import com.mams.backend.dto.AuthenticationResponse;
import com.mams.backend.dto.RegisterRequest;
import com.mams.backend.entity.Location;
import com.mams.backend.entity.User;
import com.mams.backend.repository.LocationRepository;
import com.mams.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(
            UserRepository userRepository,
            LocationRepository locationRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public AuthenticationResponse register(RegisterRequest request) {
        Location assignedBase = null;
        if (request.getAssignedBaseId() != null) {
            assignedBase = locationRepository.findById(request.getAssignedBaseId())
                    .orElseThrow(() -> new IllegalArgumentException("Base not found"));
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(request.getRole())
                .assignedBase(assignedBase)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .role(String.valueOf(user.getRole()))
                .build();
    }
}