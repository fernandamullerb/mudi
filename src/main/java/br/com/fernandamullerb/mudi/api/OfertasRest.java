package br.com.fernandamullerb.mudi.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernandamullerb.mudi.dto.OfertaDto;
import br.com.fernandamullerb.mudi.model.Oferta;
import br.com.fernandamullerb.mudi.model.Pedido;
import br.com.fernandamullerb.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public Oferta criaOferta(OfertaDto ofertaDto) {
		
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(ofertaDto.getPedidoId());
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		Oferta nova = ofertaDto.toOferta();
		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		pedidoRepository.save(pedido);
		
		return nova;
		
	}
	
}
