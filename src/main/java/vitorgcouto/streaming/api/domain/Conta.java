package vitorgcouto.streaming.api.domain;

import jakarta.persistence.*;
import vitorgcouto.streaming.api.exception.AcessoNegadoException;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Plano plano;


    public Plano getPlano() {
        return plano;
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    private List<Usuario> usuarios = new ArrayList<>();

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
        long qtdNaoAssinante = contarNaoAssinantes();

        if (qtdNaoAssinante >= plano.getLimiteUsuarios()){
            throw new IllegalStateException("Limite do plano atingido");
        }
        usuarios.add(u);
    }


    public void removerUsuario(Usuario solicitante, Usuario alvo) {
        verificarSolicitante(solicitante);
        if (alvo.isAssinante()){
            throw new IllegalStateException( "Assinante não pode ser removido");
        }
        usuarios.remove(alvo);
    }
    private void verificarSolicitante(Usuario solicitante) {
        if (!solicitante.isAssinante() || !usuarios.contains(solicitante)) {
            throw new AcessoNegadoException("Apenas o assinante da conta pode adicionar ou remover usuários");
        }
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    @OrderBy
    private  List<HistoricoVisualizacao> historico = new ArrayList<>();

    public void assistir(Usuario u, Filme f) {
            if  (!usuarios.contains(u)) {
                throw new AcessoNegadoException("Usuario não está nessa conta");
            }
            if (!u.getPerfil().podeAssistir(f.getPerfilMinimo())) {
                throw new AcessoNegadoException("Você não possui acesso a esse conteúdo");
            }
            if (!plano.temAcesso(f.getPlanoMinimo())) {
                throw new AcessoNegadoException("Não possui o plano minimo de acesso");
            }
        historico.add(new HistoricoVisualizacao(u, f));
    }

    public List<Filme> historicoDe(Usuario u) {
        return historico.stream()
                .filter(h -> h.getUsuario() == u)
                .map(h -> h.getFilme())
                .toList();
    }
    public List<Filme> filmesDisponiveis(Usuario u, List<Filme> catalogo) {
        return catalogo.stream()
                .filter(f -> u.getPerfil().podeAssistir(f.getPerfilMinimo())
                            && plano.temAcesso(f.getPlanoMinimo()))
                .toList();
    }
    private long contarNaoAssinantes() {
        return usuarios.stream()
                .filter(usuario -> !usuario.isAssinante())
                .count();
    }
    public void migrarPlano(Usuario solicitante, Plano novo) {
        verificarSolicitante(solicitante);

        if (contarNaoAssinantes()>novo.getLimiteUsuarios()) {
            throw new IllegalStateException("O plano novo não comporta a quantidade atual de usuários");
        }

        this.plano = novo;
    }
    public Long getId() {
        return id;
    }
}

