package br.com.fernandamullerb.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fernandamullerb.mudi.dto.NovoPedidoDto;
import br.com.fernandamullerb.mudi.model.Pedido;
import br.com.fernandamullerb.mudi.model.User;
import br.com.fernandamullerb.mudi.repository.PedidoRepository;
import br.com.fernandamullerb.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("formulario")
	public String formulario(NovoPedidoDto novoPedidoDto) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid NovoPedidoDto novoPedidoDto, BindingResult result) {
		
		if (result.hasErrors()) {
			return "pedido/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = novoPedidoDto.toPedido();
		pedido.setUser(user);
		
		pedidoRepository.save(pedido);
		
		return "redirect:/home";
	}
	
}
