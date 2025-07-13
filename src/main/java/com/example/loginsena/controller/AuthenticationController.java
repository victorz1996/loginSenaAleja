package com.example.loginsena.controller;

import com.example.loginsena.dto.AuthenticaRequestDto;
import com.example.loginsena.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth") // Ruta base para todos los endpoints de autenticación
public class AuthenticationController {

    // Mensajes de respuesta para la autenticación
    private final static String AUTHORED_MSG = "Autenticación satisfactoria";
    private final static String NO_AUTHORED_MSG = "Error en la autenticación";

    // Servicio que contiene la lógica de autenticación y registro
    private LoginService loginService;

    /**
     * Constructor con inyección de dependencias del LoginService.
     * Spring inyectará automáticamente una instancia del servicio.
     */
    public AuthenticationController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Endpoint POST para autenticar un usuario.
     * Verifica si el usuario y contraseña enviados existen en el sistema.
     *
     * @param authenticaRequestDto Objeto con las credenciales del usuario.
     * @return 200 OK si la autenticación es correcta, 401 Unauthorized si falla.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate (@RequestBody AuthenticaRequestDto authenticaRequestDto) {
        var serviceResponse = loginService.isAuthenticate(authenticaRequestDto);
        if (serviceResponse) return new ResponseEntity<String>(AUTHORED_MSG, HttpStatus.OK);
        return new ResponseEntity<String>(NO_AUTHORED_MSG,HttpStatus.UNAUTHORIZED);
    }

    /**
     * Endpoint POST para registrar un nuevo usuario.
     * Agrega el usuario a la lista si el username y password no están vacíos.
     *
     * @param authenticaRequestDto Objeto con los datos del nuevo usuario.
     * @return 200 OK con un mensaje indicando si el registro fue exitoso o no.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody AuthenticaRequestDto authenticaRequestDto) {
        var responseService = loginService.registerUser(authenticaRequestDto);
        return new ResponseEntity<String>(responseService,HttpStatus.OK);
    }
}
