package vitorgcouto.streaming.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vitorgcouto.streaming.api.domain.Conta;
import vitorgcouto.streaming.api.dto.ContaResponse;
import vitorgcouto.streaming.api.dto.CriarContaRequest;
import vitorgcouto.streaming.api.service.CadastroService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CadastroService cadastroService;

    public UsuarioController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }
    @PostMapping
    public ResponseEntity<ContaResponse> criar(@Valid @RequestBody CriarContaRequest request){
        Conta conta = cadastroService.criarConta(
                request.nome(), request.email(), request.senha(), request.plano());
        return ResponseEntity.status(HttpStatus.CREATED).body(ContaResponse.de(conta));
    }

}

