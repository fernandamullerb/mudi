package br.com.fernandamullerb.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fernandamullerb.mudi.model.Pedido;
import br.com.fernandamullerb.mudi.model.StatusPedido;
import br.com.fernandamullerb.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UserController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findByUser(principal.getName());
		model.addAttribute("pedidos", pedidos);
		
		return "usuario/home"; //nome da view.
	}
	
	@GetMapping("pedido/{status}")
	public String byStatus(@PathVariable String status, Model model, Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findByUserAndStatus
				(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}
	
}
