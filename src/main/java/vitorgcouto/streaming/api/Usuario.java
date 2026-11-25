package vitorgcouto.streaming.api;

public class Usuario {
    private final String nome;
    private final boolean assinante;
    private final Perfil perfil;


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
}