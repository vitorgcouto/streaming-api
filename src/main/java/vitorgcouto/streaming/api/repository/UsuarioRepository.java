package vitorgcouto.streaming.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitorgcouto.streaming.api.domain.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
