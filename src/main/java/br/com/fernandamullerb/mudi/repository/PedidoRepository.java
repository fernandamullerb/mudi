package br.com.fernandamullerb.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fernandamullerb.mudi.model.Pedido;
import br.com.fernandamullerb.mudi.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatus(StatusPedido status);

	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :username")
	List<Pedido> findByUser(@Param("username")String username);

}