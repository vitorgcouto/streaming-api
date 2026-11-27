package vitorgcouto.streaming.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Perfil;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.EmailJaCadastradoException;
import vitorgcouto.streaming.api.exception.NaoAutenticadoException;
import vitorgcouto.streaming.api.exception.RecursoNaoEncontradoException;
import vitorgcouto.streaming.api.repository.ContaRepository;
import vitorgcouto.streaming.api.repository.UsuarioRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public ContaService(ContaRepository contaRepository, UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @Transactional
    public Conta adicionarUsuario(Long solicitanteId, Long contaId, String nome, String email, String senha, Perfil perfil) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Conta não encontrada"));
        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new NaoAutenticadoException());

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException();
        }
        Usuario novo = new Usuario(nome, false, perfil);
        novo.definirCredenciais(email, encoder.encode(senha));

        conta.adicionarUsuario(solicitante, novo);
        return conta;
    }

    @Transactional
    public void removerUsuario(Long solicitanteId, Long contaId, Long alvoId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
        Usuario solicitante = usuarioRepository.findById(solicitanteId)
                .orElseThrow(() -> new NaoAutenticadoException());
        Usuario alvo = usuarioRepository.findById(alvoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        if (!conta.getUsuarios().contains(alvo)) {
            throw new RecursoNaoEncontradoException("Usuário não pertence a esta conta");
        }

        conta.removerUsuario(solicitante, alvo);
    }
}
