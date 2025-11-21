package br.projeto.redemaissocial.repository;

import br.projeto.redemaissocial.domain.PedidoAfiliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAfiliacaoRepository extends JpaRepository<PedidoAfiliacao, Long> {
}
