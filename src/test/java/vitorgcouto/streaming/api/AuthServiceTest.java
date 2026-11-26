package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import vitorgcouto.streaming.api.domain.Plano;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.CredenciaisInvalidasException;
import vitorgcouto.streaming.api.service.AuthService;
import vitorgcouto.streaming.api.service.CadastroService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AuthServiceTest {
    @Autowired private AuthService authService;
    @Autowired private CadastroService cadastroService;

   @Test
    public void deveAutenticarComCredenciaisCorretas(){
       cadastroService.criarConta("Marcos", "marcos@email.com","senha123", Plano.BASICO);

       Usuario usuario = authService.autenticar("marcos@email.com", "senha123");

       assertEquals("Marcos", usuario.getNome());
   }

   @Test
    public void naoAutenticarComSenhaInvalidas(){
       cadastroService.criarConta("Marcos", "marcos@email.com","senha123", Plano.BASICO);

       assertThrows(CredenciaisInvalidasException.class, () -> authService.autenticar("marcos@email.com", "errada"));

   }
    @Test
    void naoDeveAutenticarComEmailInexistente() {
        assertThrows(CredenciaisInvalidasException.class, () -> authService.autenticar("ninguem@email.com", "qualquer"));
    }
}