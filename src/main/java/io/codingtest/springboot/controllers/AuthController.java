package io.codingtest.springboot.controllers;


import io.codingtest.springboot.models.users.ERole;
import io.codingtest.springboot.models.users.Role;
import io.codingtest.springboot.models.users.User;
import io.codingtest.springboot.payload.request.LoginRequest;
import io.codingtest.springboot.payload.request.SignupRequest;
import io.codingtest.springboot.payload.response.JwtResponse;
import io.codingtest.springboot.payload.response.MessageResponse;
import io.codingtest.springboot.security.jwt.JwtUtils;
import io.codingtest.springboot.security.services.users.UserDetailsImpl;
import io.codingtest.springboot.security.services.users.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userDetailsService.existsByUsername(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        List<String> strRoles = signUpRequest.getRoles();
        List<Role> roles = new ArrayList<>();

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),roles);

        userDetailsService.saveUser(user);


        if (strRoles.isEmpty()) {
            Role userRole = userDetailsService.getRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            userDetailsService.addRoleToUser(signUpRequest.getEmail(),userRole.getName());
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = userDetailsService.getRole(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                        userDetailsService.addRoleToUser(signUpRequest.getEmail(),adminRole.getName());


                        break;
                    case "merch":
                        Role merchantRole = userDetailsService.getRole(ERole.ROLE_MERCHANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userDetailsService.addRoleToUser(signUpRequest.getEmail(),merchantRole.getName());

                        break;
                    default:
                        Role userRole = userDetailsService.getRole(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        userDetailsService.addRoleToUser(signUpRequest.getEmail(),userRole.getName());
                }
            });
        }


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}