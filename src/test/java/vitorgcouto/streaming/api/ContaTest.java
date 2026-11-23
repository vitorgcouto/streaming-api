package vitorgcouto.streaming.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    private Usuario marcos;

    @BeforeEach
    void preparar() {
        marcos = new Usuario("Marcos", true);
    }

    @Test
    public void contaBasicaDeveAceitarUmNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO, marcos);

        conta.adicionarUsuario(marcos, new Usuario("Caio", false));

        assertEquals(2, conta.getUsuarios().size());
    }

    @Test
    void contaBasicaNaoDeveAceitarOTerceiroNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        conta.adicionarUsuario(marcos, new Usuario("Caio", false));
        conta.adicionarUsuario(marcos, new Usuario("Maria", false));
        Usuario terceiro = new Usuario("Nicole", false);

        assertThrows(IllegalStateException.class,
                () -> conta.adicionarUsuario(marcos, terceiro));
    }

    @Test
    void contaPremiumBarraQuintoNaoAssinante() {
        Conta conta = new Conta(Plano.PREMIUM, marcos);
        conta.adicionarUsuario(marcos, new Usuario("Caio", false));
        conta.adicionarUsuario(marcos, new Usuario("Maria", false));
        conta.adicionarUsuario(marcos, new Usuario("Carol", false));
        conta.adicionarUsuario(marcos, new Usuario("Carlos", false));
        Usuario quinto = new Usuario("Nicole", false);

        assertThrows(IllegalStateException.class,
                () -> conta.adicionarUsuario(marcos, quinto));
    }

    @Test
    void assinanteNaoDeveContarNoLimiteDeNaoAssinantes() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        conta.adicionarUsuario(marcos, new Usuario("Camila", false));
        conta.adicionarUsuario(marcos, new Usuario("Maria", false));
        Usuario excedente = new Usuario("Ana", false);

        assertEquals(3, conta.getUsuarios().size());

        assertThrows(IllegalStateException.class,
                () -> conta.adicionarUsuario(marcos, excedente));
    }

    @Test
    void deveRemoverUsuarioDaConta() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        Usuario ana = new Usuario("Ana", false);
        conta.adicionarUsuario(marcos, ana);

        conta.removerUsuario(ana);

        assertEquals(1, conta.getUsuarios().size());
    }

    @Test
    void naoDeveRemoverAssinanteDaConta() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        conta.adicionarUsuario(marcos, new Usuario("Ana", false));

        assertThrows(IllegalStateException.class,
                () -> conta.removerUsuario(marcos));
        assertEquals(2, conta.getUsuarios().size());
    }

    @Test
    void contaNaoDeveAceitarNaoAssinanteComoDono() {
        Usuario naoAssinante = new Usuario("Caio", false);

        assertThrows(IllegalArgumentException.class,
                () -> new Conta(Plano.BASICO, naoAssinante));
    }
}