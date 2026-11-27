package vitorgcouto.streaming.api.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitorgcouto.streaming.api.dto.FilmeResponse;
import vitorgcouto.streaming.api.service.FilmeService;
import vitorgcouto.streaming.api.service.SessaoService;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;
    private final SessaoService sessaoService;

    public FilmeController(FilmeService filmeService, SessaoService sessaoService) {
        this.filmeService = filmeService;
        this.sessaoService = sessaoService;
    }

    @GetMapping
    public List<FilmeResponse> listar(HttpSession session) {
        Long usuarioId = sessaoService.idLogado(session);
        return filmeService.disponiveis(usuarioId).stream().map(FilmeResponse::de).toList();
    }

    @PostMapping("/{filmeId}/assistir")
    public ResponseEntity<Void> assistir(@PathVariable Long filmeId, HttpSession session) {
        Long usuarioId = sessaoService.idLogado(session);
        filmeService.assistir(usuarioId, filmeId);
        return ResponseEntity.noContent().build();
    }
}
