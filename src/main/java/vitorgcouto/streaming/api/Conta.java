package vitorgcouto.streaming.api;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private final Plano plano;
    private final List<Usuario> usuarios = new ArrayList<>();

    public Conta(Plano plano, Usuario assinante) {
        if (!assinante.isAssinante()){
            throw new IllegalArgumentException( "A conta precisa ser criada por um usuário assinante");
        }
        this.plano = plano;
        usuarios.add(assinante);

    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void adicionarUsuario(Usuario soliciante, Usuario u) {
        verificarSolicitante(soliciante);
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
    private void verificarSolicitante(Usuario solicitante) {
        if (!solicitante.isAssinante() || !usuarios.contains(solicitante)) {
            throw new AcessoNegadoException("Você não tem permissão!");
        }
    }
}
