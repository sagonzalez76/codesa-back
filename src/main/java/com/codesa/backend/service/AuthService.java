package com.codesa.backend.service;

import com.codesa.backend.dto.*;
import com.codesa.backend.model.Rol;
import com.codesa.backend.model.Usuario;
import com.codesa.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codesa.backend.exception.AuthenticationException;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.USER)
                .build();
        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(usuario.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Las credenciales son incorrectas.");
        } catch (DisabledException e) {
            throw new AuthenticationException("La cuenta está deshabilitada.");
        } catch (UsernameNotFoundException e) {
            throw new AuthenticationException("El correo no está registrado.");
        } catch (AuthenticationException e) {
            throw new AuthenticationException("Error de autenticación.");
        }

        String token = jwtService.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
