package vitorgcouto.streaming.api.dto;

import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Plano;

import java.util.List;

public record ContaResponse(Long id, Plano plano, List<UsuarioResponse> usuarios) {
    public static ContaResponse de(Conta c){
        return new ContaResponse(c.getId(), c.getPlano(),
                c.getUsuarios().stream().map(UsuarioResponse::de).toList());
    }
}
