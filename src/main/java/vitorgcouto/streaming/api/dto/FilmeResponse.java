package vitorgcouto.streaming.api.dto;

import vitorgcouto.streaming.api.domain.Filme;
import vitorgcouto.streaming.api.domain.Perfil;
import vitorgcouto.streaming.api.domain.Plano;

public record FilmeResponse(Long id, String nome, Perfil perfilminimo, Plano planoMinimo) {
    public static FilmeResponse de(Filme f) {
        return new FilmeResponse(f.getId(), f.getNome(), f.getPerfilMinimo(), f.getPlanoMinimo());
    }
}
