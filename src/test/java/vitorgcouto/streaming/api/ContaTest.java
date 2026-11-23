package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ContaTest {
    @Test
    public void contaBasicaDeveAceitarUmNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO);
        Usuario filho = new Usuario("Caio", false);

        conta.adicionarUsuario(filho);

        assertEquals( 1 , conta.getUsuarios().size());
    }

    @Test
    void contaBasicaNaoDeveAceitarOTerceiroNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO);
        conta.adicionarUsuario(new Usuario("Caio", false));   // 1º
        conta.adicionarUsuario(new Usuario("Maria", false));   // 2º
        Usuario terceiro = new Usuario("Nicole", false);


        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(terceiro));
    }

    @Test
    void contaPremiumBarraQuintoNaoAssinante() {
        Conta conta = new Conta(Plano.PREMIUM);
        conta.adicionarUsuario(new Usuario("Caio", false));
        conta.adicionarUsuario(new Usuario("Maria", false));
        conta.adicionarUsuario(new Usuario("Carol", false));
        conta.adicionarUsuario(new Usuario("Carlos", false));
        Usuario quinto = new Usuario("Nicole", false);


        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(quinto));
    }
}

