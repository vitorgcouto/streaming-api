package vitorgcouto.streaming.api.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Plano;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.EmailJaCadastradoException;
import vitorgcouto.streaming.api.repository.ContaRepository;
import vitorgcouto.streaming.api.repository.UsuarioRepository;

@Service
public class CadastroService {
        private final ContaRepository contaRepository;
        private final PasswordEncoder encoder;
        private final UsuarioRepository usuarioRepository;

        public CadastroService(UsuarioRepository usuarioRepository,ContaRepository contaRepository, PasswordEncoder encoder) {
            this.contaRepository = contaRepository;
            this.encoder = encoder;
            this.usuarioRepository = usuarioRepository;
        }

        @Transactional
        public Conta criarConta( String nome, String email, String senha, Plano plano){
            if(usuarioRepository.existsByEmail(email)){
                throw new EmailJaCadastradoException();
            }
            Usuario dono = new Usuario(nome, true);
            dono.definirCredenciais(email, encoder.encode(senha));
            Conta conta = new Conta(plano, dono);
            return contaRepository.save(conta);
        }
}