package vitorgcouto.streaming.api.domain;

import java.time.LocalDateTime;

public class HistoricoVisualizacao {
    private final Usuario usuario;
    private final Filme filme;
    private final LocalDateTime visualizadoEm = LocalDateTime.now();

    public HistoricoVisualizacao(Usuario usuario, Filme filme) {
        this.usuario = usuario;
        this.filme = filme;
    }

    public Usuario getUsuario() { return usuario; }
    public Filme getFilme() { return filme; }
    public LocalDateTime getVisualizadoEm() { return visualizadoEm; }
}