package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ContaTest {
    @Test
    public void contaBasicaDeveAceitarUmNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO, new Usuario("Marcos", true));
        Usuario filho = new Usuario("Caio", false);

        conta.adicionarUsuario(filho);

        assertEquals( 2 , conta.getUsuarios().size());
    }

    @Test
    void contaBasicaNaoDeveAceitarOTerceiroNaoAssinante() {
        Conta conta = new Conta(Plano.BASICO, new Usuario("Marcos", true));
        conta.adicionarUsuario(new Usuario("Caio", false));
        conta.adicionarUsuario(new Usuario("Maria", false));
        Usuario terceiro = new Usuario("Nicole", false);


        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(terceiro));
    }

    @Test
    void contaPremiumBarraQuintoNaoAssinante() {
        Conta conta = new Conta(Plano.PREMIUM, new Usuario("Marcos", true));
        conta.adicionarUsuario(new Usuario("Caio", false));
        conta.adicionarUsuario(new Usuario("Maria", false));
        conta.adicionarUsuario(new Usuario("Carol", false));
        conta.adicionarUsuario(new Usuario("Carlos", false));
        Usuario quinto = new Usuario("Nicole", false);


        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(quinto));
    }
    @Test
    void assinanteNaoDeveContarNoLimiteDeNaoAssinantes() {
        Conta conta = new Conta(Plano.BASICO, new Usuario("Marcos", true));
        conta.adicionarUsuario(new Usuario("Camila", false));
        conta.adicionarUsuario(new Usuario("Maria", false));
        Usuario excedente = new Usuario("Ana", false);


        assertEquals(3, conta.getUsuarios().size());

        assertThrows(IllegalStateException.class, () -> conta.adicionarUsuario(excedente));
    }
    @Test
    void deveRemoverUsuarioDaConta() {
        Conta conta = new Conta(Plano.BASICO, new Usuario("Marcos", true));
        Usuario ana = new Usuario("Ana", false);
        conta.adicionarUsuario(ana);

        conta.removerUsuario(ana);

        assertEquals(1, conta.getUsuarios().size());
    }
    @Test
    void naoDeveRemoverAssinanteDaConta() {
        Usuario assinante = new Usuario("Marcos", true);
        Conta conta = new Conta(Plano.BASICO, assinante);
        conta.adicionarUsuario(new Usuario("Ana", false));

        assertThrows(IllegalStateException.class, () -> conta.removerUsuario(assinante));
        assertEquals(2, conta.getUsuarios().size());
    }
}

