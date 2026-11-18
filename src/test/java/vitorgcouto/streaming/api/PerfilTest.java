package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerfilTest {
    @Test
    void adultoDevePoderAssistirConteudoInfantil() {
        Perfil usuario = Perfil.ADULTO;
        Perfil exigido = Perfil.INFANTIL;

        boolean resultado = usuario.podeAssistir(exigido);

        assertTrue(resultado);
    }
}
