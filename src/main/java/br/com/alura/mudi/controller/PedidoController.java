package br.com.alura.mudi.controller;

import br.com.alura.mudi.dto.NovoPedidoDTO;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.model.Usuario;
import br.com.alura.mudi.repository.PedidoRepository;
import br.com.alura.mudi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String home(Model model, Principal principal) {

        Usuario usuario = usuarioRepository.findByUsername(principal.getName());

        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuario);
        model.addAttribute("pedidos", pedidos);

        return "pedidos/lista";
    }

    @GetMapping("/{status}")
    public String pedidosPorStatus(@PathVariable("status") String status, Model model, Principal principal) {

        Usuario usuario = usuarioRepository.findByUsername(principal.getName());

        List<Pedido> pedidos = pedidoRepository.findByUsuarioAndStatus(usuario, StatusPedido.valueOf(status.toUpperCase(Locale.ROOT)));

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);

        return "pedidos/lista";
    }

    @GetMapping("cadastrar")
    public String cadastrar(NovoPedidoDTO novoPedido) {
        return "pedidos/formulario";
    }

    @PostMapping("salvar")
    public String salvar(@Valid NovoPedidoDTO novoPedido, BindingResult resultadoValidacao) {

        if(resultadoValidacao.hasErrors()) {
            return "pedidos/formulario";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByUsername(username);

        Pedido pedido = novoPedido.toPedido();
        pedido.setUsuario(usuario);

        pedidoRepository.save(pedido);

        return "redirect:/pedidos";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/pedidos";
    }

}
