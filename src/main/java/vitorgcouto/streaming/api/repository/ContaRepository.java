package vitorgcouto.streaming.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vitorgcouto.streaming.api.domain.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
