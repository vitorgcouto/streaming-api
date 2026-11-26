package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import vitorgcouto.streaming.api.domain.Plano;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.CredenciaisInvalidasException;
import vitorgcouto.streaming.api.exception.EmailJaCadastradoException;
import vitorgcouto.streaming.api.repository.UsuarioRepository;
import vitorgcouto.streaming.api.service.CadastroService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CadastroServiceTest {

    @Autowired
    private CadastroService service;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder encoder;

    @Test
    void deveCriarAssinanteComSenhaCriptografada() {
        service.criarConta("Marcos", "marcos@email.com", "senha123", Plano.BASICO);

        Usuario salvo = usuarioRepository.findByEmail("marcos@email.com").orElseThrow();

        assertTrue(salvo.isAssinante());
        assertNotEquals("senha123", salvo.getSenha());
        assertTrue(encoder.matches("senha123", salvo.getSenha()));
    }
    @Test
    void naoDeveCriarContaComEmailRepetido() {
        service.criarConta("Marcos", "marcos@email.com", "senha123", Plano.BASICO);

        assertThrows(EmailJaCadastradoException.class,
                () -> service.criarConta("Outro", "marcos@email.com", "senha456", Plano.PREMIUM));
    }
}
