package vitorgcouto.streaming.api.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import vitorgcouto.streaming.api.exception.NaoAutenticadoException;

@Component
public class SessaoService {

    public Long idLogado(HttpSession session) {
        Object id = session.getAttribute("usuarioId");
        if (id == null) {
            throw new NaoAutenticadoException();
        }
        return (Long) id;
    }
}
