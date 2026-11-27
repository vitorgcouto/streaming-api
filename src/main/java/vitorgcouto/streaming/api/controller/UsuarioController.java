package vitorgcouto.streaming.api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.dto.ContaResponse;
import vitorgcouto.streaming.api.dto.CriarContaRequest;
import vitorgcouto.streaming.api.dto.FilmeResponse;
import vitorgcouto.streaming.api.service.CadastroService;
import vitorgcouto.streaming.api.service.ContaService;
import vitorgcouto.streaming.api.service.FilmeService;
import vitorgcouto.streaming.api.service.SessaoService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CadastroService cadastroService;
    private final FilmeService filmeService;
    private final SessaoService sessaoService;

    public UsuarioController(CadastroService cadastroService, FilmeService filmeService, SessaoService sessaoService) {
        this.filmeService = filmeService;
        this.sessaoService = sessaoService;
        this.cadastroService = cadastroService;
    }
    @PostMapping
    public ResponseEntity<ContaResponse> criar(@Valid @RequestBody CriarContaRequest request){
        Conta conta = cadastroService.criarConta(
                request.nome(), request.email(), request.senha(), request.plano());
        return ResponseEntity.status(HttpStatus.CREATED).body(ContaResponse.de(conta));
    }
    @GetMapping("/{usuarioId}/historico")
    public List<FilmeResponse> historico(@PathVariable Long usuarioId, HttpSession session) {
        Long solicitanteId = sessaoService.idLogado(session);
        return filmeService.historico(solicitanteId, usuarioId).stream()
                .map(FilmeResponse::de)
                .toList();
    }

}

