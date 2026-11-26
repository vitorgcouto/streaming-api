package vitorgcouto.streaming.api.dto;

import vitorgcouto.streaming.api.domain.Perfil;
import vitorgcouto.streaming.api.domain.Usuario;

public record UsuarioResponse(Long id, String nome, boolean assinante, Perfil perfil) {
    public static UsuarioResponse de(Usuario u){
        return new UsuarioResponse(u.getId(), u.getNome(), u.isAssinante(), u.getPerfil());
    }
}
