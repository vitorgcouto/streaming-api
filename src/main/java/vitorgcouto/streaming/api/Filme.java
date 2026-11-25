package vitorgcouto.streaming.api;

public class Filme {
    private final String nome;
    private final Perfil perfilMinimo;
    private final Plano planoMinimo;

    public Filme(String nome, Perfil perfilMinimo, Plano planoMinimo) {
        this.nome = nome;
        this.perfilMinimo = perfilMinimo;
        this.planoMinimo = planoMinimo;
    }

    public String getNome() { return nome; }
    public Perfil getPerfilMinimo() { return perfilMinimo; }
    public Plano getPlanoMinimo() { return planoMinimo; }
}