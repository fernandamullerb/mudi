package br.com.fernandamullerb.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fernandamullerb.mudi.model.Pedido;
import br.com.fernandamullerb.mudi.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatus(StatusPedido status);

}