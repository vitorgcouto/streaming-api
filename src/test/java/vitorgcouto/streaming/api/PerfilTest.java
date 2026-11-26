package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;
import vitorgcouto.streaming.api.domain.Perfil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerfilTest {
    @Test
    void adultoDevePoderAssistirConteudoInfantil() {
        Perfil usuario = Perfil.ADULTO;
        Perfil exigido = Perfil.INFANTIL;

        boolean resultado = usuario.podeAssistir(exigido);

        assertTrue(resultado);
    }

    @Test
    void infantilNaoDevePoderAssistirConteudoAdulto() {
        Perfil usuario = Perfil.INFANTIL;
        Perfil exigido = Perfil.ADULTO;

        boolean resultado = usuario.podeAssistir(exigido);

        assertFalse(resultado);
    }

    @Test
    void adolescenteDevePoderAssistirConteudoAdolescente() {
        Perfil usuario = Perfil.ADOLESCENTE;
        Perfil exigido = Perfil.ADOLESCENTE;

        boolean resultado = usuario.podeAssistir(exigido);

        assertTrue(resultado);
    }

    @Test
    void adolescenteNaoDevePoderAssistirConteudoAdulto() {
        Perfil usuario = Perfil.ADOLESCENTE;
        Perfil exigido = Perfil.ADULTO;

        boolean resultado = usuario.podeAssistir(exigido);

        assertFalse(resultado);
    }
}
