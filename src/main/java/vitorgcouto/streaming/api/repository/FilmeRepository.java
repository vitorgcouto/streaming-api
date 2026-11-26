package vitorgcouto.streaming.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitorgcouto.streaming.api.domain.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
