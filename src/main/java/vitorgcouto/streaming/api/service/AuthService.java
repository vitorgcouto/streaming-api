package vitorgcouto.streaming.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.CredenciaisInvalidasException;
import vitorgcouto.streaming.api.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new CredenciaisInvalidasException());

        if  (!encoder.matches(senha, usuario.getSenha())) {
            throw new CredenciaisInvalidasException();
        }
        return usuario;
    }


}
