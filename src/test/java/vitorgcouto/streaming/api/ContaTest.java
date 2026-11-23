package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
    @Test
    void assinanteNaoDeveContarNoLimiteDeNaoAssinantes() {
        Conta conta = new Conta(Plano.BASICO);
        conta.adicionarUsuario(new Usuario("Marcos", true));
        conta.adicionarUsuario(new Usuario("Camila", false));
        conta.adicionarUsuario(new Usuario("Maria", false));
        Usuario excedente = new Usuario("Ana", false);


        assertEquals(3, conta.getUsuarios().size());

        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(excedente));
    }
    @Test
    void deveRemoverUsuarioDaConta() {
        Conta conta = new Conta(Plano.BASICO);
        Usuario ana = new Usuario("Ana", false);
        conta.adicionarUsuario(new Usuario("Marcos", true));
        conta.adicionarUsuario(ana);

        conta.removerUsuario(ana);

        assertEquals(1, conta.getUsuarios().size());
    }
    @Test
    void naoDeveRemoverAssinanteDaConta() {
        Conta conta = new Conta(Plano.BASICO);
        Usuario ana = new Usuario("Ana", true);
        conta.adicionarUsuario(new Usuario("Marcos", false));
        conta.adicionarUsuario(ana);

        assertThrows(IllegalStateException.class, () -> conta.removerUsuario(ana));
        assertEquals(2, conta.getUsuarios().size());
    }
}

