package br.com.alura.mudi.api;

import br.com.alura.mudi.dto.NovaOfertaDTO;
import br.com.alura.mudi.model.Oferta;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.repository.OfertaRepository;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @PostMapping
    public Oferta fazOferta(@Valid @RequestBody NovaOfertaDTO novaOferta) {

        Oferta oferta = novaOferta.toOferta();

        Optional<Pedido> optional = pedidoRepository.findById(novaOferta.getPedidoId());
        if(optional.isEmpty())
            return null;
        Pedido pedido = optional.get();
        oferta.setPedido(pedido);

        ofertaRepository.save(oferta);

        return oferta;
    }

}
