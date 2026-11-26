package vitorgcouto.streaming.api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.dto.LoginRequest;
import vitorgcouto.streaming.api.service.AuthService;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request,
                                      HttpSession session) {
        Usuario usuario = authService.autenticar(request.email(), request.senha());
        session.setAttribute("usuarioId", usuario.getId());
        return ResponseEntity.ok().build();
    }
}