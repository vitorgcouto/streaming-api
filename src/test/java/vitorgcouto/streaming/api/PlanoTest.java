package vitorgcouto.streaming.api;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PlanoTest {

    @Test
    public void limiteDoPlanoBasicoDeveSerDois(){
        int limite = Plano.BASICO.getLimiteUsuarios();
        assertEquals(2, limite);
    }

    @Test
    public void limiteDoPlanoPremiumDeveSerQuatro(){
        int limite = Plano.PREMIUM.getLimiteUsuarios();
        assertEquals(4, limite);
    }
    @Test
    public void premiumDevePoderAssistirConteudoDoPlanoBasico(){
        Plano plano = Plano.PREMIUM;
        Plano exigido = Plano.BASICO;

        boolean resultado = plano.temAcesso(exigido);

        assertTrue(resultado);
    }
    @Test
    public void basicoNaoPodeAssistirConteudoDoPlanoPremium(){
        Plano plano = Plano.BASICO;
        Plano exigido = Plano.PREMIUM;

        boolean resultado = plano.temAcesso(exigido);

        assertFalse(resultado);
    }
}
