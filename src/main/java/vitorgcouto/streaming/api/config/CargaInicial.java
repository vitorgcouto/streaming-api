package vitorgcouto.streaming.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vitorgcouto.streaming.api.domain.Filme;
import vitorgcouto.streaming.api.domain.Perfil;
import vitorgcouto.streaming.api.domain.Plano;
import vitorgcouto.streaming.api.repository.FilmeRepository;

import java.util.List;

@Component
public class CargaInicial implements CommandLineRunner {

    private final FilmeRepository filmeRepository;

    public CargaInicial(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (filmeRepository.count() == 0) {
            filmeRepository.saveAll(List.of(
                    new Filme("Aventura Animada",   Perfil.INFANTIL,    Plano.BASICO),
                    new Filme("Desenho Premium",    Perfil.INFANTIL,    Plano.PREMIUM),
                    new Filme("Comédia Jovem",      Perfil.ADOLESCENTE, Plano.BASICO),
                    new Filme("Ficção Científica",  Perfil.ADOLESCENTE, Plano.PREMIUM),
                    new Filme("Drama Adulto",       Perfil.ADULTO,      Plano.BASICO),
                    new Filme("Thriller Adulto",    Perfil.ADULTO,      Plano.PREMIUM)));

        }
    }
}
