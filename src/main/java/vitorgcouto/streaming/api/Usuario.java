package vitorgcouto.streaming.api;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String nome;
    private final boolean assinante;

    public Usuario(String nome, boolean assinante) {
        this.nome = nome;
        this.assinante = assinante;
    }

    public String getNome() {return nome;}
    public boolean isAssinante() {return assinante;}

}
