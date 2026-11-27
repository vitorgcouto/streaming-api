package vitorgcouto.streaming.api.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.dto.AdicionarUsuarioRequest;
import vitorgcouto.streaming.api.dto.ContaResponse;
import vitorgcouto.streaming.api.service.ContaService;
import vitorgcouto.streaming.api.service.SessaoService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;
    private final SessaoService sessaoService;

    public ContaController(ContaService contaService, SessaoService sessaoService) {
        this.contaService = contaService;
        this.sessaoService = sessaoService;
    }

    @PostMapping("/{contaId}/usuarios")
    public ResponseEntity<ContaResponse> adicionar(@PathVariable Long contaId,
                                                   @Valid @RequestBody AdicionarUsuarioRequest request,
                                                   HttpSession session){
        Long solicitanteId = sessaoService.idLogado(session);
        Conta conta = contaService.adicionarUsuario(solicitanteId, contaId,
                request.nome(), request.email(), request.senha(), request.perfil());
        return ResponseEntity.status(HttpStatus.CREATED).body(ContaResponse.de(conta));
    }
}

