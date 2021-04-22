package br.com.alura.mudi.controller;

import br.com.alura.mudi.dto.NovoPedidoDTO;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping("cadastrar")
    public String cadastrar(NovoPedidoDTO novoPedido) {
        return "pedidos/formulario";
    }

    @PostMapping("salvar")
    public String salvar(@Valid NovoPedidoDTO novoPedido, BindingResult resultadoValidacao) {

        if(resultadoValidacao.hasErrors()) {
            return "pedidos/formulario";
        }

        Pedido pedido = novoPedido.toPedido();
        repository.save(pedido);

        return "redirect:/home";
    }

}
