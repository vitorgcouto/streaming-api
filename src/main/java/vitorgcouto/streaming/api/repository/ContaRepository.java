package vitorgcouto.streaming.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitorgcouto.streaming.api.domain.Usuario;

public interface ContaRepository extends JpaRepository<Usuario, Long> {

}
