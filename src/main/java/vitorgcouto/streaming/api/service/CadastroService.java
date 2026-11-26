package vitorgcouto.streaming.api.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Plano;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.repository.ContaRepository;

@Service
public class CadastroService {
        private final ContaRepository contaRepository;
        private final PasswordEncoder encoder;

        public CadastroService(ContaRepository contaRepository, PasswordEncoder encoder) {
            this.contaRepository = contaRepository;
            this.encoder = encoder;
        }
        @Transactional
        public Conta criarConta(String nome, String email, String senha, Plano plano){
            Usuario dono = new Usuario(nome, true);
            dono.definirCredenciais(email, encoder.encode(senha));
            Conta conta = new Conta(plano, dono);
            return contaRepository.save(conta);
        }
}