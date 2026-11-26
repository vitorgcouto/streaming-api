package vitorgcouto.streaming.api.domain;

import jakarta.persistence.*;

@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Perfil perfilMinimo;

    @Enumerated(EnumType.STRING)
    private Plano planoMinimo;

    protected Filme() {
    }

    public Filme(String nome, Perfil perfilMinimo, Plano planoMinimo) {
        this.nome = nome;
        this.perfilMinimo = perfilMinimo;
        this.planoMinimo = planoMinimo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Perfil getPerfilMinimo() { return perfilMinimo; }
    public Plano getPlanoMinimo() { return planoMinimo; }
}