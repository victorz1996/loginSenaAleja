package com.example.loginsena.service;

import com.example.loginsena.dto.AuthenticaRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    // Constantes para usuario administrador y mensajes
    private final static String USER_ADMIN = "admin";
    private final static String PASS_ADMIN = "admin";
    private final static String USER_REGISTER_MSG = "Usuario nuevo registrado";
    private final static String USER_NO_REGISTER_MSG = "No se pudo registrar el usuario";

    // Lista que almacena los usuarios autenticados o registrados
    private List<AuthenticaRequestDto> authenticaRequestDtoList;

    /**
     * Constructor del servicio.
     * Inicializa la lista de usuarios con un usuario administrador por defecto.
     */
    public LoginService(List<AuthenticaRequestDto> authenticaRequestDtoList) {
        this.authenticaRequestDtoList = new ArrayList<>();
        this.authenticaRequestDtoList.add(new AuthenticaRequestDto(USER_ADMIN, PASS_ADMIN));
    }

    /**
     * Verifica si un usuario está autenticado comparando sus credenciales (username y password)
     * con los usuarios existentes en la lista.
     *
     * @param authenticaRequestDto Objeto que contiene las credenciales del usuario a autenticar.
     * @return true si las credenciales coinciden con algún usuario existente, false en caso contrario.
     */
    public Boolean isAuthenticate (AuthenticaRequestDto authenticaRequestDto) {
        var list = authenticaRequestDtoList.stream().filter(user-> user.getUsername().equals(authenticaRequestDto.getUsername()) &&
                user.getPassword().equals(authenticaRequestDto.getPassword())).toList();
        return !list.isEmpty();
    }

    /**
     * Registra un nuevo usuario si ambos campos (usuario y contraseña) no están vacíos.
     *
     * @param authenticaRequestDto Objeto con los datos del usuario a registrar.
     * @return Mensaje indicando si el usuario fue registrado exitosamente o no.
     */
    public String registerUser (AuthenticaRequestDto authenticaRequestDto) {
        if (!authenticaRequestDto.getPassword().isEmpty() && !authenticaRequestDto.getUsername().isEmpty()) {
            authenticaRequestDtoList.add(authenticaRequestDto);
            return USER_REGISTER_MSG;
        }
        return USER_NO_REGISTER_MSG;
    }
}
