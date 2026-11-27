package vitorgcouto.streaming.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Usuario;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("select c from Conta c join c.usuarios u where u = :usuario")
    Optional<Conta> buscarPorUsuario(@Param("usuario") Usuario usuario);

}
