package vitorgcouto.streaming.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
