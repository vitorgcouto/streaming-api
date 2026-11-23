package vitorgcouto.streaming.api;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private final Plano plano;
    private final List<Usuario> usuarios = new ArrayList<>();

    public Conta(Plano plano) {
        this.plano = plano;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void adicionarUsuario(Usuario u) {
        long qtdNaoAssinante = usuarios.stream()
                .filter(usuario -> !usuario.isAssinante())
                .count();

        if (qtdNaoAssinante >= plano.getLimiteUsuarios()){
            throw new IllegalStateException("Limite do plano atingido");
        }
        usuarios.add(u);
    }

    public void removerUsuario(Usuario u) {
        if (u.isAssinante()){
            throw new IllegalStateException( "Assinante não pode ser removido");
        }
        usuarios.remove(u);
    }
}
