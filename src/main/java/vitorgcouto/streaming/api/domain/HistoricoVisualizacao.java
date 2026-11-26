package vitorgcouto.streaming.api.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HistoricoVisualizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Filme filme;

    private LocalDateTime visualizadoEm = LocalDateTime.now();

    protected HistoricoVisualizacao() {
    }

    public HistoricoVisualizacao(Usuario usuario, Filme filme) {
        this.usuario = usuario;
        this.filme = filme;
    }

    public Usuario getUsuario() { return usuario; }
    public Filme getFilme() { return filme; }
    public LocalDateTime getVisualizadoEm() { return visualizadoEm; }
}