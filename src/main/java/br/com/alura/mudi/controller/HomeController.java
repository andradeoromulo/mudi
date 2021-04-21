package br.com.alura.mudi.controller;

import br.com.alura.mudi.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        Pedido pedido = new Pedido();
        pedido.setNomeProduto("Kindle Oasis 8GB");
        pedido.setUrlProduto("https://www.amazon.com.br/gp/product/B07L57H5X4");
        pedido.setUrlImagem("https://images-na.ssl-images-amazon.com/images/I/61KvQd6lXCL._AC_SL1001_.jpg");
        pedido.setDescricao("Kindle Oasis 8GB");

        List<Pedido> pedidos = Arrays.asList(pedido);

        model.addAttribute("pedidos", pedidos);

        return "home";
    }

}
