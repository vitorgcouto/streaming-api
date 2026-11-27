package vitorgcouto.streaming.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.domain.Filme;
import vitorgcouto.streaming.api.domain.Usuario;
import vitorgcouto.streaming.api.exception.NaoAutenticadoException;
import vitorgcouto.streaming.api.exception.RecursoNaoEncontradoException;
import vitorgcouto.streaming.api.repository.ContaRepository;
import vitorgcouto.streaming.api.repository.FilmeRepository;
import vitorgcouto.streaming.api.repository.UsuarioRepository;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;

    public FilmeService(FilmeRepository filmeRepository, UsuarioRepository usuarioRepository,
                        ContaRepository contaRepository) {
        this.filmeRepository = filmeRepository;
        this.usuarioRepository = usuarioRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional(readOnly = true)
    public List<Filme> disponiveis(Long usuarioId) {
        Usuario usuario = carregarUsuario(usuarioId);
        Conta conta = carregarConta(usuario);
        return conta.filmesDisponiveis(usuario, filmeRepository.findAll());
    }

    @Transactional
    public void assistir(Long usuarioId, Long filmeId) {
        Usuario usuario = carregarUsuario(usuarioId);
        Conta conta = carregarConta(usuario);
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filme não encontrado"));
        conta.assistir(usuario, filme);
    }

    private Usuario carregarUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NaoAutenticadoException());
    }

    private Conta carregarConta(Usuario usuario) {
        return contaRepository.buscarPorUsuario(usuario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
    }
}