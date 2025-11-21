package br.projeto.redemaissocial.controller;

import br.projeto.redemaissocial.domain.PedidoAfiliacao;
import br.projeto.redemaissocial.dto.PedidoAfiliacaoRequest;
import br.projeto.redemaissocial.dto.PedidoAfiliacaoResponse;
import br.projeto.redemaissocial.service.PedidoAfiliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoAfiliacaoController {

    private final PedidoAfiliacaoService service;

    public PedidoAfiliacaoController(PedidoAfiliacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoAfiliacaoResponse> criar(@RequestBody PedidoAfiliacaoRequest req) {
        PedidoAfiliacao p = service.criarPedido(req.getRequesterName(), req.getEmail());
        PedidoAfiliacaoResponse resp = toResponse(p);
        return ResponseEntity.created(URI.create("/api/pedidos/" + p.getId())).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAfiliacaoResponse> getById(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PedidoAfiliacaoResponse> listar() {
        return service.listarTodos().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoAfiliacaoResponse> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        PedidoAfiliacao.Status s;
        try {
            s = PedidoAfiliacao.Status.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        PedidoAfiliacao updated = service.atualizarStatus(id, s);
        return ResponseEntity.ok(toResponse(updated));
    }

    private PedidoAfiliacaoResponse toResponse(PedidoAfiliacao p) {
        PedidoAfiliacaoResponse r = new PedidoAfiliacaoResponse();
        r.setId(p.getId());
        r.setRequesterName(p.getRequesterName());
        r.setEmail(p.getEmail());
        r.setStatus(p.getStatus().name());
        r.setCreatedAt(p.getCreatedAt());
        return r;
    }
}
