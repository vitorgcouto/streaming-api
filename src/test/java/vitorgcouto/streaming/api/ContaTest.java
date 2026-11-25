package vitorgcouto.streaming.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        conta.removerUsuario(marcos, ana);

        assertEquals(1, conta.getUsuarios().size());
    }

    @Test
    void naoDeveRemoverAssinanteDaConta() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        conta.adicionarUsuario(marcos, new Usuario("Ana", false));

        assertThrows(IllegalStateException.class,
                () -> conta.removerUsuario(marcos, marcos));
        assertEquals(2, conta.getUsuarios().size());
    }

    @Test
    void contaNaoDeveAceitarNaoAssinanteComoDono() {
        Usuario naoAssinante = new Usuario("Caio", false);

        assertThrows(IllegalArgumentException.class,
                () -> new Conta(Plano.BASICO, naoAssinante));
    }
    @Test
    void naoAssinanteNaoDeveAdicionarUsuario() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        Usuario camila = new Usuario("Camila", false);
        conta.adicionarUsuario(marcos, camila);

        Usuario ana = new Usuario("Ana", false);

        assertThrows(AcessoNegadoException.class, () -> conta.adicionarUsuario(camila, ana));

        assertEquals( 2, conta.getUsuarios().size());
    }
    @Test
    void naoAssinanteNaoDeveRemoverUsuario() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        Usuario camila = new Usuario("Camila", false);
        Usuario ana = new Usuario("Ana", false);
        conta.adicionarUsuario(marcos, camila);
        conta.adicionarUsuario(marcos, ana);


        assertThrows(AcessoNegadoException.class, () -> conta.removerUsuario(camila, ana));

        assertEquals(3, conta.getUsuarios().size());
    }

    @Test
    void assinanteDeOutraContaNaoDeveAdicionarUsuario() {
        Conta conta = new Conta(Plano.BASICO, marcos);
        Usuario paulo = new Usuario("Paulo", true);   // assinante, mas NÃO está nesta conta
        Usuario ana = new Usuario("Ana", false);

        assertThrows(AcessoNegadoException.class, () -> conta.adicionarUsuario(paulo, ana));
    }
    @Test
    void adultoEmContaPremiumDeveAssistirFilmeAdultoPremium() {
        Conta conta = new Conta(Plano.PREMIUM, marcos);
        Usuario ana = new Usuario("Ana", false, Perfil.ADULTO);
        conta.adicionarUsuario(marcos, ana);

        Filme filme = new Filme("Filme de adulto", Perfil.ADULTO,Plano.PREMIUM);

        conta.assistir(ana, filme);

        assertEquals(1, conta.historicoDe(ana).size());
    }
    @Test
    void infantilNaoDeveAssistirFilmeAdulto() {
        Usuario caio = new Usuario("Caio", false, Perfil.INFANTIL);
        Conta conta = new Conta(Plano.PREMIUM, marcos);
        conta.adicionarUsuario(marcos, caio);

        Filme filme = new Filme("Filme de adulto", Perfil.ADULTO,Plano.BASICO);

        assertThrows(AcessoNegadoException.class, () -> conta.assistir(caio, filme));

        assertEquals(0, conta.historicoDe(caio).size());
    }
    @Test
    void contaBasicaNaoDeveAssistirFilmePremium() {
        Usuario caio = new Usuario("Caio", false, Perfil.ADULTO);
        Conta conta = new Conta(Plano.BASICO, marcos);
        conta.adicionarUsuario(marcos, caio);

        Filme filme = new Filme("Filme de adulto", Perfil.ADULTO,Plano.PREMIUM);

        assertThrows(AcessoNegadoException.class, () -> conta.assistir(caio, filme));

        assertEquals(0, conta.historicoDe(caio).size());
    }
    @Test
    void historicoDeveManterOrdemERepeticoes() {
        Conta conta = new Conta(Plano.PREMIUM, marcos);
        Usuario ana = new Usuario("Ana", false, Perfil.ADULTO);
        conta.adicionarUsuario(marcos, ana);

        Filme a = new Filme("Filme A",  Perfil.ADULTO,Plano.PREMIUM);
        Filme b = new Filme("Filme B",  Perfil.ADULTO,Plano.PREMIUM);

        conta.assistir(ana, a);
        conta.assistir(ana, b);
        conta.assistir(ana, a);

        assertEquals(List.of(a, b ,a), conta.historicoDe(ana));
    }
    @Test
    void deveListarSomenteFilmesPermitidosParaPerfilEPlano(){
        Conta conta = new Conta(Plano.BASICO, marcos);
        Usuario caio = new Usuario("Caio", false, Perfil.INFANTIL);
        conta.adicionarUsuario(marcos, caio);

        Filme liberado = new Filme ("Desenho", Perfil.INFANTIL, Plano.BASICO);
        Filme barradoPerfil = new Filme ("Filme adulto", Perfil.ADULTO, Plano.BASICO);
        Filme barradoPlano = new Filme ("Desenho Premium", Perfil.INFANTIL, Plano.PREMIUM);
        List<Filme> catalogo = List.of(liberado, barradoPerfil,barradoPlano);

        List<Filme> resultado = conta.filmesDisponiveis(caio, catalogo);

        assertEquals(List.of(liberado), resultado);
    }
}