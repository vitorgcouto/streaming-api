package vitorgcouto.streaming.api.domain;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private  String nome;
    private  boolean assinante;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    protected Usuario() {

    }


    public Usuario(String nome, boolean assinante) {
        this(nome, assinante, Perfil.ADULTO);
    }

    public Usuario(String nome, boolean assinante, Perfil perfil) {
        this.nome = nome;
        this.assinante = assinante;
        this.perfil = perfil;
    }

    public String getNome() { return nome; }
    public boolean isAssinante() { return assinante; }
    public Perfil getPerfil() { return perfil; }

    public Long getId() {
        return id;
    }
}