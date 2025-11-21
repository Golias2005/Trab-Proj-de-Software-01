package br.projeto.redemaissocial.service;

import br.projeto.redemaissocial.domain.PedidoAfiliacao;
import br.projeto.redemaissocial.repository.PedidoAfiliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoAfiliacaoService {

    private final PedidoAfiliacaoRepository repository;

    public PedidoAfiliacaoService(PedidoAfiliacaoRepository repository) {
        this.repository = repository;
    }

    public PedidoAfiliacao criarPedido(String requesterName, String email) {
        PedidoAfiliacao p = new PedidoAfiliacao(requesterName, email);
        return repository.save(p);
    }

    public Optional<PedidoAfiliacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<PedidoAfiliacao> listarTodos() {
        return repository.findAll();
    }

    public PedidoAfiliacao atualizarStatus(Long id, PedidoAfiliacao.Status status) {
        PedidoAfiliacao p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        p.setStatus(status);
        return repository.save(p);
    }
}
